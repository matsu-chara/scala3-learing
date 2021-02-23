package contextual

object A:
  class TC
  given tc: TC = ???
  def f(using TC) = ???

object B:
  import A.*
  import A.given // or import A.given TC or import A.{given T1, ..., given Tn}

object Instances:
  given intOrd: Ordering[Int] = ???
  given listOrd[T: Ordering]: Ordering[List[T]] = ???
  given ec: scala.concurrent.ExecutionContext = ???

// 以下で Ordering[Int], Ordering[List[T]], ecがimport可
import Instances.{given Ordering[?], given scala.concurrent.ExecutionContext}

// ecのように名前でimportも可
import Instances.{ec, given Ordering[?]}