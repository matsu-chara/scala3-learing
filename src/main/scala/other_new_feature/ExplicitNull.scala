package other_new_feature


object ExplicitNull:
  val x: String | Null = null
//  val y = x.trim // compile error
  val y = x.nn.trim //  assert(x != null); x.asInstanceOf[T]

  class CC(val x: Int, val next: CC | Null)
  
  def foo =
    var xs: CC | Null = CC(1, CC(2, null))
    // xs is trackable, since all assignments are in the same method
    while xs != null do
      // xs: CC
      val xsx: Int = xs.x
      val xscpy: CC = xs
      xs = xscpy // since xscpy is non-null, xs still has type C after this line
      // xs: CC
      xs = xs.next // after this assignment, xs can be null again
    // xs: CC | Null
      
//    def bar =
      // import scala.language.unsafeNulls // unsafeNullsを有効にするとコンパイル通るはずだけど通らない・・？
//      val s: String | Null = ???
//      val a: String = s // unsafely convert String | Null to String
//      
//      val b1 = s.trim // call .trim on String | Null unsafely
//      val b2 = b1.length
