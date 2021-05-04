package macro_tutorial

import scala.quoted._

object QuotedCode:
  object Expr:
    def apply[T](x: T)(using Quotes, ToExpr[T]): Expr[T] =
      summon[ToExpr[T]].apply(x)
