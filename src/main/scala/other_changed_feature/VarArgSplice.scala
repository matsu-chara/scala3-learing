package other_changed_feature

object VarArgSplice:
  val arr = Array(0, 1, 2, 3)
  val lst = List(arr*)                    // vararg splice argument
  lst match
    case List(0, 1, xs*) => println(xs)  // binds xs to Seq(2, 3)
    case List(1, _*) => ()                  // wildcard pattern
    case _ => ()

