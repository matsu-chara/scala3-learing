package other_changed_feature

object OptionLessPaternMatching:
  // Boolean Match
  object Even:
    def unapply(s: String): Boolean = s.size % 2 == 0

  "even" match
    case s @ Even() => println(s"$s has an even number of characters")
    case s          => println(s"$s has an odd number of characters")

  // Product Match
  class FirstChars(s: String) extends Product:
    def _1 = s.charAt(0)
    def _2 = s.charAt(1)
  
    // Not used by pattern matching: Product is only used as a marker trait.
    def canEqual(that: Any): Boolean = ???
    def productArity: Int = ???
    def productElement(n: Int): Any = ???
  
  object FirstChars:
    def unapply(s: String): FirstChars = new FirstChars(s)
  
  "Hi!" match
    case FirstChars(char1, char2) =>
      println(s"First: $char1; Second: $char2") // First: H; Second: i

  // Single Match
  class Nat(val x: Int):
    def get: Int = x
    def isEmpty = x < 0
  
  object Nat:
    def unapply(x: Int): Nat = new Nat(x)
  
  5 match
    case Nat(n) => println(s"$n is a natural number")
    case _      => ()

  // Name-based Match
  object ProdEmpty:
    def _1: Int = ???
    def _2: String = ???
    def isEmpty = true
    def unapply(s: String): this.type = this
    def get = this

  "" match
    case ProdEmpty(_, _) => ??? // (Int, String)でマッチ
    case _ => ()

  // Sequence Match
  object CharList:
    def unapplySeq(s: String): Option[Seq[Char]] = Some(s.toList)

  "example" match
    case CharList(c1, c2, c3, c4, _, _, _) =>
      println(s"$c1,$c2,$c3,$c4")
    case _ =>
      println("Expected *exactly* 7 characters!")

  // Product-Sequence Match
  class Foo(val name: String, val children: Int *)
  object Foo:
    def unapplySeq(f: Foo): Option[(String, Seq[Int])] =
      Some((f.name, f.children))
  
  def foo(f: Foo) = f match
    case Foo(name, x, y, ns : _*) =>
    case Foo(name, ns : _*) =>