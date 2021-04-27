package macro_tutorial

object Inline:
  inline val pi = 3.141592653589793
  inline val pie = "🥧"

  // inline化されたあとに定数畳み込み(constant folding)という最適化が走ってコンパイル時に評価される
  val pi2 = pi + pi // val pi2 = 6.283185307179586
  val pie2 = pie + pie // val pie2 = "🥧🥧"

  val pix = 3.141592653589793
  // inline val pix2 = pix + pix // inline value must have a literal constant type

  inline def logged[T](level: Int, message: => String)(inline op: T): T =
    println(s"[$level]Computing $message")
    val res = op
    println(s"[$level]Result of $message: $res")
    res

//  logged(logLevel, getMessage()) {
//    computeSomething()
//  }
//  がよばれると
//  val level   = logLevel
//  def message = getMessage()
//  
//  println(s"[$level]Computing $message")
//  val res = computeSomething()
//  println(s"[$level]Result of $message: $res")
//  res
// に展開される
