package macro_tutorial

object CompileTimeOperation:
  import scala.compiletime.{codeOf, error}
  inline def doSomething(inline mode: Boolean): Unit =
    if mode then ???
    else if !mode then ???
    else error("Mode must be a known value but got: " + codeOf(mode))

  doSomething(true) // ok
  doSomething(false) // ok
//  val bool: Boolean = ???
//  doSomething(bool) // error: Mode must be a known value but got: bool
