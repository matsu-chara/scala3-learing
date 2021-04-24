package dropped_features

object WildcardInitializer:
  import scala.compiletime.uninitialized
  var x: String = _ // サポートされているが将来消される
  var y: String = uninitialized // こちらが正式

