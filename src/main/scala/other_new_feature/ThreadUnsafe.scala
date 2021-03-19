package other_new_feature

class ThreadUnsafe:
  import scala.annotation.threadUnsafe
  
  class Hello:
    @threadUnsafe lazy val x: Int = 1
