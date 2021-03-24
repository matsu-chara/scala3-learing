package metaprogramming

import scala.quoted.{Expr, Exprs, Quotes, Type, Varargs}

object Macros:
  inline def assert(inline expr: Boolean): Unit =
    ${ assertImpl('expr) }

  def assertImpl(expr: Expr[Boolean])(using Quotes) = '{
    if !$expr then
      throw AssertionError(s"failed assertion: ${${ showExpr(expr) }}")
  }

  def showExpr[T](expr: Expr[T])(using Quotes): Expr[String] =
    val code: String = expr.show
    Expr(code)

  def to[T: Type, R: Type](f: Expr[T] => Expr[R])(using Quotes): Expr[T => R] =
    '{ (x: T) => ${ f('x) } }

  def from[T: Type, R: Type](f: Expr[T => R])(using Quotes): Expr[T] => Expr[R] =
    (x: Expr[T]) => '{ $f($x) }

  enum Exp:
    case Num(n: Int)
    case Plus(e1: Exp, e2: Exp)
    case Var(x: String)
    case Let(x: String, e: Exp, in: Exp)

  import Exp.*
  val exp = Plus(Plus(Num(2), Var("x")), Num(4)): @unchecked
  val letExp = Let("x", Num(3), exp): @unchecked


  def compile(e: Exp, env: Map[String, Expr[Int]])(using Quotes): Expr[Int] =
    e match
      case Num(n) =>
        Expr(n)
      case Plus(e1, e2) =>
        '{ 
            ${ compile(e1, env) } + ${ compile(e2, env) }
          }
      case Var(x) =>
        env(x)
      case Let(x, e, body) =>
        '{ 
          val y: Int = ${ compile(e, env) }
          ${ compile(body, env + (x -> 'y)) }
         }


  def map[T](arr: Expr[Array[T]], f: Expr[T] => Expr[Unit])
            (using Type[T], Quotes): Expr[Unit] = '{
    var i: Int = 0
    while i < ($arr).length do
      val element: T = ($arr)(i)
      ${f('element)}
      i += 1
    }
  
  def sum(arr: Expr[Array[Int]])(using Quotes): Expr[Int] = '{
    var sum = 0
    ${ map(arr, x => '{sum += $x}) }
    sum
    }
  
  inline def sum_m(arr: Array[Int]): Int = ${sum('arr)}

  inline def sum2(inline args: Int*): Int = ${ sumExpr('args) }

  private def sumExpr(argsExpr: Expr[Seq[Int]])(using Quotes): Expr[Int] =
    argsExpr match
      case Varargs(_ @ Exprs(argValues)) =>
        Expr(argValues.sum) // expressionとして拾えるならコンパイル時にsumする
      case Varargs(argExprs) =>
        val staticSum: Int = argExprs.map(_.value.getOrElse(0)).sum
        val dynamicSum: Seq[Expr[Int]] = argExprs.filter(_.value.isEmpty)
        dynamicSum.foldLeft(Expr(staticSum))((acc, arg) => '{ $acc + $arg })
      case _ =>
        '{ $argsExpr.sum }
  
  // optimize {
  //   sum(sum(1, a, 2), 3, b)
  //}
  // が6 + a + bに最適化されるquotted patterns
  def sum3(args: Int*): Int = args.sum

  inline def optimize(inline arg: Int): Int = ${ optimizeExpr('arg) }

  private def optimizeExpr(body: Expr[Int])(using Quotes): Expr[Int] =
    body match
      case '{ sum3() } => Expr(0)
      case '{ sum3($n) } => n
      case '{ sum3(${Varargs(args)}: _*) } => sumExpr(args)
      case body => body
  
  private def sumExpr(args1: Seq[Expr[Int]])(using Quotes): Expr[Int] =
    def flatSumArgs(arg: Expr[Int]): Seq[Expr[Int]] = arg match
      case '{ sum3(${Varargs(subArgs)}: _*) } => subArgs.flatMap(flatSumArgs)
      case arg => Seq(arg)

    val args2 = args1.flatMap(flatSumArgs)
    val staticSum: Int = args2.map(_.value.getOrElse(0)).sum
    val dynamicSum: Seq[Expr[Int]] = args2.filter(_.value.isEmpty)
    dynamicSum.foldLeft(Expr(staticSum))((acc, arg) => '{ $acc + $arg })