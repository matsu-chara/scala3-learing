package dropped_features

object NonLocalReturns:
  import scala.util.control.NonLocalReturns.*

  extension [T](xs: List[T])
    def has(elem: T): Boolean = returning {
      for x <- xs do
        if x == elem then throwReturn(true)
      false
    }

  def test(): Unit =
    val xs = List(1, 2, 3, 4, 5)
    assert(xs.has(2) == xs.contains(2))
