package dropped_features

// package objectがなくても全部トップレベルで定義可能
type Labelled[T] = (String, T)
val a: Labelled[Int] = ("count", 1)
def b = a._2

case class C()

extension (x: C) def pair(y: C) = (x, y)
