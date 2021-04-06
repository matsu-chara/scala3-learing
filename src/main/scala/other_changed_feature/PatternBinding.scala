package other_changed_feature

object PatternBinding:
  val xs: List[Any] = List(1, 2, 3)
  val (x: String) :: _ = xs   // -source futureをつけるとerror: pattern's type String is more specialized than the right-hand side expression's type Anyがでる

  // 以下はOK
  val pair = (1, true)
  val (a, b) = pair

  val elems: List[Any] = List((1, 2), "hello", (3, 4))
  for (c, d) <- elems yield (d, c) // scala2では"hello"がfilterされるがこちらも-source futureつけると error: pattern's type (Any, Any) is more specialized than the right-hand side expression's type Any

  for case (e, f) <- elems yield (f, e)  // caseをつけるとfilterされる returns List((2, 1), (4, 3))
