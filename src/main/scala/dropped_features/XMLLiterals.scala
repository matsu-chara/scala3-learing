package dropped_features

object XMLLiterals:
  // not foundになるのでコメントアウト（多分scala-xml入れれば治る？)
//  import dotty.xml.interpolator.*

  case class Person(name: String) { override def toString = name }

  def test: Unit =
    val bill = Person("Bill")
    val john = Person("John")
    val mike = Person("Mike")
    val todoList = List(
      (bill, john, "Meeting", "Room 203, 11:00am"),
      (john, mike, "Holiday", "March 22-24")
    )
    // XML literals (to be dropped)
  // scala-xmlに依存するとコンパイルが通る
//    val mails1 = for (from, to, heading, body) <- todoList yield
//      <message>
//        <from>{from}</from><to>{to}</to>
//        <heading>{heading}</heading><body>{body}</body>
//      </message>
//  println(mails1)

  // XML string interpolation
//    val mails2 = for (from, to, heading, body) <- todoList yield xml"""
//        <message>
//          <from>${from}</from><to>${to}</to>
//          <heading>${heading}</heading><body>${body}</body>
//          </message>"""
//    println(mails2)
