package macro_tutorial

object Reflection:
  import scala.quoted.* // Import `quotes`, `Quotes` and `Expr`

  def f(x: Expr[Int])(using Quotes): Expr[Int] =
    import quotes.reflect.*
    val tree: Term = x.asTerm
    val expr: Expr[Int] = tree.asExprOf[Int]
    expr

  def debug(x: Expr[Int])(using Quotes): Expr[Int] =
    import quotes.reflect.*
    val tree: Tree = ???
    println(tree.show(using Printer.TreeStructure))

    tree match
      case Ident(_)     => ???
      case Select(_, _) => ???
      case _ =>
        throw new MatchError(tree.show(using Printer.TreeStructure))
