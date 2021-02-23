package enums.enumerations

enum Color:
  case Red, Green, Blue

enum Color2(val rgb: Int):
  case Red   extends Color2(0xFF0000)
  case Green extends Color2(0x00FF00)
  case Blue  extends Color2(0x0000FF)


object Coloring:
  @main def foo: Unit =
    println(Color.Red)
    println(Color.Red.ordinal)
    println(Color.valueOf("Red"))
    println(Color.values.mkString(", "))
    println(Color.fromOrdinal(0))

enum Planet(mass: Double, radius: Double):
  private final val G = 6.67300E-11
  def surfaceGravity = G * mass / (radius * radius)
  def surfaceWeight(otherMass: Double) = otherMass * surfaceGravity

  case Mercury extends Planet(3.303e+23, 2.4397e6)
  case Venus   extends Planet(4.869e+24, 6.0518e6)

object Planetting:
  def check: Unit =
    println(Planet.Mercury.surfaceGravity)