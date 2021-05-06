package macro_tutorial

import scala.quoted._

object QuotedCode:
//  object Expr:
//    def apply[T](x: T)(using Quotes, ToExpr[T]): Expr[T] =
//      summon[ToExpr[T]].apply(x)

  def valueOfBoolean(x: Expr[Boolean])(using Quotes): Option[Boolean] =
    x match
      case '{ true }  => Some(true)
      case '{ false } => Some(false)
      case _          => None

  def valueOfBooleanOption(x: Expr[Option[Boolean]])(using
      Quotes
  ): Option[Option[Boolean]] =
    x match
      case '{ Some(true) }  => Some(Some(true))
      case '{ Some(false) } => Some(Some(false))
      case '{ None }        => Some(None)
      case _                => None

  def exprOfOption[T: Type](x: Expr[Option[T]])(using Quotes): Option[Expr[T]] =
    x match
      case '{ Some($x: T) } => Some(x) // x: Expr[T]
      case _                => None

  def valueOf(x: Expr[Any])(using Quotes): Option[Any] =
    x match
      case '{ $x: Boolean } => valueOfBoolean(x) // x: Expr[Boolean]
      case '{ $x: Option[Boolean] } =>
        valueOfBooleanOption(x) // x: Expr[Option[Boolean]]
      case '{ ($ls: List[Int]).sum } => None // type annotationが必要なこともある
      case _                         => None

  def exprOfOption2[T: Type](x: Expr[Option[T]])(using
      Quotes
  ): Option[Expr[T]] =
    x match
      case '{ Some($x: T) } => Some(x) // x: Expr[T]
      // ^^^ type ascription with generic type T
      case _ => None

  def exprOfOptionOf3[T: Type](x: Expr[Option[Any]])(using
      Quotes
  ): Option[Expr[T]] =
    x match
      case '{ Some($x: T) } => Some(x) // x: Expr[T]
      case _                => None

  def exprOptionToList(x: Expr[Option[Any]])(using
      Quotes
  ): Option[Expr[List[Any]]] =
    x match
      case '{ Some($x: t) } =>
        // ^^^ 型変数Tをバインドできる
        Some('{ List[t]($x) }) // x: Expr[List[t]] として使える
      case '{ None } => Some('{ Nil })
      case _         => None

  def fuseMap[T: Type](x: Expr[List[T]])(using Quotes): Expr[List[T]] =
    x match {
      case '{
            type u
            type v
            ($ls: List[`u`])
              .map($f: `u` => `v`)
              .map($g: `v` => T)
          } =>
        '{ $ls.map(x => $g($f(x))) }
      case _ => x
    }

  def mirrorFields[T: Type](using Quotes): Expr[List[String]] =
    Type.of[T] match
      case '[field *: fields] => '{  ${Expr(Type.show[field])} :: ${mirrorFields[fields]} }
      case '[EmptyTuple] => '{Nil}
      case _ => '{compiletime.error("Expected known tuple but got: " + ${Expr(Type.show[T])})}

//    mirrorFields[EmptyTuple]         // Nil
//    mirrorFields[(Int, String, Int)] // List("Int", "String", "Int")
//    mirrorFields[Tuple]              // error: Expected known tuple but got: Tuple
