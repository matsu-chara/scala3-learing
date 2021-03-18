package other_new_feature

class MatchableTrait:
  class C(val x: String):
    override def equals(that: Any): Boolean =
      that.asInstanceOf[Matchable] match
        case that: C => this.x == that.x
        case _ => false
