package macro_tutorial

import scala.quoted.{Expr, Exprs, Quotes, Type, Varargs}

object Macro:
  inline def inspect(inline x: Any): Any = ${ inspectCode('x) }

  def inspectCode(x: Expr[Any])(using Quotes): Expr[Any] =
    println(x.show)
    x

  inline def power(inline x: Double, inline n: Int) =
    ${ powerCode('x, 'n) }

  def pow(x: Double, n: Int): Double =
    if n == 0 then 1 else x * pow(x, n - 1)

  def powerCode(
      x: Expr[Double],
      n: Expr[Int]
  )(using Quotes): Expr[Double] =
    val value: Double = pow(x.valueOrError, n.valueOrError)
    Expr(value)

  def debugPowerCode(
      x: Expr[Double],
      n: Expr[Int]
  )(using Quotes): Expr[Double] =
    println(s"""powerCode
       |  x := ${x.show}
       |  n := ${n.show}""".stripMargin)
    val code = powerCode(x, n)
    println(s"  code := ${code.show}")
    code

  inline def sumNow(inline nums: Int*): Int =
    ${ sumCode('nums) }

  def sumCode(nums: Expr[Seq[Int]])(using Quotes): Expr[Int] =
    nums match
      case Varargs(numberExprs) => // numberExprs: Seq[Expr[Int]]
        val numbers: Seq[Int] = numberExprs.map(_.valueOrError)
        Expr(numbers.sum)
      case _ =>
        scala.sys.error(
          "Expected explicit argument" +
            "Notation `args: _*` is not supported."
        )
