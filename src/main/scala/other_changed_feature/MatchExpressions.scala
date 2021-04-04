package other_changed_feature

object MatchExpressions:
  List("").match { // ドットつきでよびだせる
    case Nil => "empty"
    case _   => "nonempty"
  } match { // chainingできる
    case "empty"    => 0
    case "nonempty" => 1
  }
