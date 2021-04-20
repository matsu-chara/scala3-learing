package dropped_features

object AutoApplication:
  def next(): String = ""

//  def a = next    // method next in object AutoApplication must be called with () argumentでコンパイルエラー
  def a = next()

  def b = 3.toString // java由来のメソッドは()なしで呼べる

