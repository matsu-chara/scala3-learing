package other_new_feature

import scala.reflect.{TypeTest, Typeable}

object TypeTestX:
  val tt: TypeTest[Any, String] =
    new TypeTest[Any, String]:
      def unapply(x: Any): Option[x.type & String] = x match
        case s: (x.type & String) => Some(s)
        case _ => None

  def f[X, Y](x: X)(using tt: TypeTest[X, Y]): Option[Y] = x match
    case x: Y => Some(x) // x: Yとすれば勝手にtt(x)に展開される。 (x @ Y(_)なら tt(x @ Y(_)))になる）
    case _ => None

  def f2[T: Typeable]: Boolean =
    "abc" match
      case x: T => true
      case _ => false

object TypeTestExample:
  val aaa = TypeTestX.f[AnyRef, String]("acb")(using TypeTestX.tt)

  TypeTestX.f2[String] // true
  TypeTestX.f2[Int] // false
