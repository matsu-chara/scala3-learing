package metaprogramming

@main def macroCheck: Unit = 
  println(Macros.assert(1 == 1))
  println(Macros.assert(1 == 1+1-2+1+1))
