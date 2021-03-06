package other_new_feature

trait Greeting(val name: String):
  def msg = s"How are you, $name"

class C extends Greeting("Bob"):
  println(msg)

trait FormalGreeting extends Greeting:
  override def msg = s"How do you do, $name"

class E extends Greeting("Bob"), FormalGreeting