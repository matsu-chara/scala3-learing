package dropped_features

object WeakConformance:
  def foo(l: List[Double]) = println(l)

  val n: Int = 3
  val c: Char = 'X'
  val d: Double = math.sqrt(3.0)
  val l = List(n, c, d) // 以前はList[Double]だったけどweak conformanceがdropされたからList[AnyVal]
//  foo(l) コンパイルエラー
