package other_changed_feature

object ChangesInOverloadResolution:
  def f(x: Int)(y: String): Int = 0
  def f(x: Int)(y: Int): Int = 0
  
  f(3)("") // scala2ではコンパイルエラーだがscala3では通る

  def g(x: Int, g2: Int => Int) = g2(x)
  def g(x: String, g2: String => String) = g2(x)
  g("a", _.toUpperCase.nn)
  g(2, _ * 2)