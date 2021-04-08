package other_changed_feature

object AutomaticEtaExpansion:
  def m(x: Boolean, y: String)(z: Int): List[Int] = ???
  val f1: (Boolean, String) => Int => List[Int] = m
  val f2: Int => List[Int] = m(true, "abc")
