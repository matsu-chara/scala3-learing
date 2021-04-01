package other_changed_feature

object RulesForOperators:
  import scala.annotation.targetName

  trait MultiSet[T]:
    infix def union(other: MultiSet[T]): MultiSet[T]

    def difference(other: MultiSet[T]): MultiSet[T]

    @targetName("intersection")
    def *(other: MultiSet[T]): MultiSet[T]

  val s1, s2: MultiSet[Int] = ???

  s1 union s2         // OK
  s1 `union` s2       // also OK but unusual
  s1.union(s2)        // also OK

  s1.difference(s2)   // OK
  s1 `difference` s2  // OK
  s1 difference s2    // gives a deprecation warning

  s1 * s2             // OK
  s1 `*` s2           // also OK, but unusual
  s1.*(s2)            // also OK, but unusual

  val str = "hello"
  ++ " world"
  ++ "!"

  def condition(x: Int, xs: List[Int]) =
    x > 0
    ||
    xs.exists(_ > 0)
    || xs.isEmpty
