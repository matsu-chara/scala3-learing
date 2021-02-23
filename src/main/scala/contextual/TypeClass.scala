package contextual

trait SemiGroup[T]:
  extension (x: T) def combine (y: T): T

trait Monoid[T] extends SemiGroup[T]:
  def unit: T

given Monoid[String] with
  extension (x: String) def combine (y: String): String = x.concat(y)
  def unit: String = ""

given Monoid[Int] with
  extension (x: Int) def combine (y: Int): Int = x + y
  def unit: Int = 0

object Monoid:
  def apply[T](using m: Monoid[T]) = m

def combineAll[T: Monoid](xs: List[T]): T =
  xs.foldLeft(Monoid[T].unit)(_.combine(_))

trait Functor[F[_]]:
  extension [A](x: F[A])
    def map[B](f: A => B): F[B]

given Functor[List] with
  extension [A](x: List[A])
    def map[B](f: A => B): List[B] =
      x.map(f)

// assertTransformation(List("a1", "b1"), List("a", "b"), elt => s"${elt}1")
def assertTransformation[F[_]: Functor, A, B](expected: F[B], original: F[A], mapping: A => B): Unit =
  assert(expected == original.map(mapping))

