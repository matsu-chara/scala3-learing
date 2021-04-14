package dropped_features

object DoWhile:
  def a =
    var i = -5
    while
      i += 1
      i == 0
    do ()
