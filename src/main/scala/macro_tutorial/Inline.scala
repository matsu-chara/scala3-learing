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
//  def message = getMessage() <- by-nameパラメータだとdefになる
//
//  println(s"[$level]Computing $message")
//  val res = computeSomething() <- inlineパラメータなのでinline化される。（by-valueだと先頭でval定義されてしまうのでprintlnの前に実行されてしまう
//  println(s"[$level]Result of $message: $res")
//  res
// に展開される

  class Logger:
    def log(x: Any): Unit = println(x)

  class RefinedLogger extends Logger:
    override def log(x: Any): Unit = println("Any: " + x)
    def log(x: String): Unit = println("String: " + x)

  inline def logged[T](logger: Logger, x: T): Unit =
    logger.log(x)

  // logged(new RefinedLogger, "foo") をinline化すると
  // val logger = new RefinedLogger
  // val x = "foo"
  // logger.log(x)
  // なのでlog(x: String)のほうが呼び出されそうだが、inline前後でsemanticsを変えては行けないというルールがあるので
  // logged[T]から呼べるlogger.logであるlog(x: Any)の方が呼ばれる

  inline def power(x: Double, inline n: Int): Double =
    inline if (n == 0) 1.0 // ifだけだとunrollingされない可能性がある。inline ifならコンパイル時に定数かどうか判断して確実にunrollingする（定数でない場合はコンパイルエラー）
    else inline if (n % 2 == 1) x * power(x, n - 1)
    else power(x * x, n / 2)

  trait InlineLogger:
    // inlineを強制できる（取ることもできるがオーバーロードの結果が変わる(refinedLoggerの例みたいに)
    // InlineLoggerのlogを直接呼ぶことはできない（何をinline化すればいいか不明なため）
    inline def log(inline x: Any): Unit

  class PrintLogger extends InlineLogger:
    inline def log(inline x: Any): Unit = println(x)

  // 以下のようにinline化する前提なら呼べる
  inline def logged(logger: InlineLogger, x: Any) =
    logger.log(x)

  // 返り値がIntやStringに静的に解決される（whiteboxマクロ的な使い方）
  transparent inline def default(inline name: String): Any =
    inline if name == "Int" then 0
    else inline if name == "String" then ""
    else ???
