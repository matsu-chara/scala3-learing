package metaprogramming

@main def macroCheck: Unit =
  println(Macros.assert(1 == 1))
  println(Macros.assert(1 == (if (false) 1 else 2)))
