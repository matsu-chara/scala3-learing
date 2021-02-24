package contextual

case class Circle(x: Double, y: Double, radius: Double)

object EE:
  extension (c: Circle)
    def circumference: Double = c.radius * math.Pi * 2

object EEE:
  import EE.circumference
  Circle(1, 1, 1).circumference


extension [T](xs: List[T])
  def second = xs.tail.head

extension [T: Numeric](x: T) // extension [T](x: T)(using n: Numeric[T])でもOK
  def + (y: T): T = summon[Numeric[T]].plus(x, y)

extension [T](xs: List[T])
  def sumBy[U: Numeric](f: T => U): U = ???

object FFF:
  val sum1 = List("a", "bb", "ccc").sumBy[Int](_.length)
  val sum2 = sumBy[String](List("a", "bb", "ccc"))(_.length)
  val sum3 = sumBy[String](List("a", "bb", "ccc"))[Int](_.length)


extension (ss: Seq[String])
  def longestStrings: Seq[String] =
    val maxLength = ss.map(_.length).max
    ss.filter(_.length == maxLength)

  def longestString: String = longestStrings.head