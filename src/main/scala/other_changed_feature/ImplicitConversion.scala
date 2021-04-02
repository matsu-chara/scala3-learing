package other_changed_feature

object ImplicitConversion:
  import scala.language.implicitConversions
  implicit def ordT[T, S](
                           implicit conv: Conversion[T, S],
                           ordS: Ordering[S]
                         ): Ordering[T] = ???
  
  class A(val x: Int) // The type for which we want an `Ordering`
  
  // Convert `A` to a type for which an `Ordering` is available:
  implicit val AToInt: Conversion[A, Int] = _.x
  
  implicitly[Ordering[Int]] // Ok, exists in the standard library
  implicitly[Ordering[A]] // Ok, will use the implicit conversion from
  // `A` to `Int` and the `Ordering` for `Int`.
