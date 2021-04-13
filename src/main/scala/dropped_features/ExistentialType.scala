package dropped_features

object ExistentialType:
  type AAA = Map[_ <: AnyRef, Int] // forSomeは廃止

