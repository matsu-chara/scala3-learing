package other_new_feature

import scala.concurrent.ExecutionContext

class ChangesInImplicitResolution:
  class C:
    val ctx: ExecutionContext = ???
    // implicit val x = ???    // error: type must be given explicitly

    // implicit def y = ???    // error: type must be given explicitly
    
    val y =
      implicit val ctx = this.ctx // ok

  def f(implicit i: C) = {
    def g(implicit j: C) = {
      implicitly[C] // jに解決される
    }
  }