package other_new_feature

object OptionalBraces:
  // -Yindent-colonsが必要
  val f = List(1,2).map:
    x =>
      val y = x - 1
      y * y
