package other_new_feature

object WildcardArgumentsInType:
  type Foo = List[?]
  type Bar = Map[? <: AnyRef, ? >: Null]