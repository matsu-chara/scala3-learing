package other_new_feature

object UniversalApply:
  class MyStringBuilder(s: String):
    def this() = this("")

  val a = MyStringBuilder("abc")  // old: new StringBuilder("abc")
  val b = MyStringBuilder()       // old: new StringBuilder()