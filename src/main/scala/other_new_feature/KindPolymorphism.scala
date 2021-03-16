package other_new_feature

object KindPolymorphism:
  def f[T <: AnyKind] = ???
  f[Int]
  f[List]
  f[Map]