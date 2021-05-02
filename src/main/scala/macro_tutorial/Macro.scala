package macro_tutorial

import scala.quoted.{Expr, Exprs, Quotes, Type, Varargs}

object Macro:
  inline def inspect(inline x: Any): Any = ${ inspectCode('x) }

  def inspectCode(x: Expr[Any])(using Quotes): Expr[Any] =
    println(x.show)
    x
