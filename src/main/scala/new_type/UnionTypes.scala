package new_type

case class UserName(name: String)
case class Password(hash: String)

def help(id: UserName | Password) =
  val user = id match
    case UserName(name) => println(name)
    case Password(hash) => println(hash)

trait CC[+T]
trait D
trait E
class AA extends CC[AA] with D
class BB extends CC[BB] with D with E

def ab: AA|BB = {
  new AA
}
val _ab: CC[AA | BB] & D = ab

import scala.collection.mutable.ListBuffer
val x: ListBuffer[Either[Int, String]] = ListBuffer(Right("foo"), Left(0))

trait X { def hello: String }
trait Y { def hello: String }
//def test(x: X | Y) = x.hello // error: value `hello` is not a member of X | Y

trait Z { def hello: String }
trait XX extends Z
trait YY extends Z

def test(x: XX | YY) = x.hello // ok as `hello` is a member of the join of XX | YY which is Z
