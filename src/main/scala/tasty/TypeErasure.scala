package tasty

object TypeErasure:
  val xs: List[Int] = List(1, 2, 3)
  // .class
// public scala.collection.immutable.List<java.lang.Object> xs();

  val x = xs(0)
// .class
//  int x = (Int) xs.get(0)      // castされる

// erasureだけでなくユニオン、インターセクション、パラメータ付きのtraitもclassファイルにはない
// tastyなら情報がある
// runtimeはList[Object]のままなので情報は得られない。

// tastyファイルの互換性があればいいのでscala 2.13とscala 3の互換性を保つのが容易
// cala 2.13.5にはTASTyリーダーがあり、Scala 3コンパイラは2.13のPickleフォーマットも読むこともできる
