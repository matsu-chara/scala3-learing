package dropped_features

object PrivateThisAndProtectedThis:
  class Foo:
    // 両方ともphased outする
    // privateなメソッドがthisを介してのみアクセスしていたらコンパイラが勝手にprivate[this]相当として扱ってくれる
    private[this] def foo: String = "foo"

    // proctected[this]はunsoundなので削除された
    protected[this] def bar: String = "bar"
