package other_new_feature

object ParameterUntupling:
  val xs: List[(Int, Int)] = List((2,3), (4,5))

  xs map {
    (x, y) => x + y
  }