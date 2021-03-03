package metaprogramming

object Config:
  inline val logging = false

object Logger:

  private var indent = 0

  inline def log[T](msg: String, indentMargin: =>Int)(op: => T): T =
    if Config.logging then
      println(s"${"  " * indent}start $msg")
      indent += indentMargin
      val result = op
      indent -= indentMargin
      println(s"${"  " * indent}$msg = $result")
      result
    else op
end Logger

inline def funkyAssertEquals(actual: Double, expected: =>Double, inline delta: Double): Unit =
  if (actual - expected).abs > delta then
    throw new AssertionError(s"difference between ${expected} and ${actual} was larger than ${delta}")

class A
class B extends A:
  def m = true

transparent inline def choose(b: Boolean): A =
  if b then new A else new B

object B:
  val obj1 = choose(true)  // static type is A
  val obj2: B = choose(false) // static type is B
  // val a = false; obj3: B = choose(a) だとコンパイルエラー

  transparent inline def zero: Int = 0
  val one: 1 = zero + 1

  inline def update(delta: Int) =
    inline if delta >= 0 then delta + 1
    else delta - 1

  transparent inline def g(x: Any): Any =
    inline x match
      case x: String => (x, x) // Tuple2[String, String](x, x)
      case x: Double => x


object C:
  import scala.compiletime.{constValue, S}

  transparent inline def toIntC[N]: Int =
    inline constValue[N] match
      case 0        => 0
      case _: S[n1] => 1 + toIntC[n1]

//  def foo = constValue[Int] // これはエラー  not a constant type: Int; cannot take constValue

  inline val ctwo = toIntC[2]

  import scala.compiletime.erasedValue

  inline def defaultValue[T] =
    inline erasedValue[T] match
      case _: Byte    => Some(0: Byte)
      case _: Char    => Some(0: Char)
      case _: Short   => Some(0: Short)
      case _: Int     => Some(0)
      case _: Long    => Some(0L)
      case _: Float   => Some(0.0f)
      case _: Double  => Some(0.0d)
      case _: Boolean => Some(false)
      case _: Unit    => Some(())
      case _          => None