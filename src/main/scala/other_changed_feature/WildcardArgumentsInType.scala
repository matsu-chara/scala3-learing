package other_changed_feature

object WildcardArgumentsInType:
  type Foo = List[?]
  type Bar = Map[? <: AnyRef, ? >: Null]