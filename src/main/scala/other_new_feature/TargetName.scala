package other_new_feature

object TargetName:
  import scala.annotation.targetName
  
  object VecOps:
    extension [T](xs: Vector[T])
      @targetName("append")
      def ++=(ys: Vector[T]): Vector[T] = ???
  
    val a = Vector(1) ++= Vector(2)
//    val b = Vector(1).append(Vector) // javaからは呼べるけどscalaからは呼べない

    @targetName("f_string")
    def f(x: => String): Int = x.length
    def f(x: => Int): Int = x + 1  // OK