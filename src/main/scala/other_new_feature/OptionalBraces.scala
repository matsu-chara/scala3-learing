package other_new_feature

object OptionalBraces:
  import language.experimental.fewerBraces
  val f = List(1,2).map:
    x =>
      val y = x - 1
      y * y
