import scala.deriving.Mirror
import scala.compiletime.{erasedValue, summonInline}

inline def summonAll[T <: Tuple]: List[Eq1[_]] =
  inline erasedValue[T] match
    case _: EmptyTuple => Nil
    case _: (t *: ts) => summonInline[Eq1[t]] :: summonAll[ts]

trait Eq1[T]:
  def Eq1v(x: T, y: T): Boolean

object Eq1:
  given Eq1[Int] with
    def Eq1v(x: Int, y: Int) = x == y

  def check(elem: Eq1[_])(x: Any, y: Any): Boolean =
    elem.asInstanceOf[Eq1[Any]].Eq1v(x, y)

  def iterator[T](p: T) = p.asInstanceOf[Product].productIterator

  def Eq1Sum[T](s: Mirror.SumOf[T], elems: => List[Eq1[_]]): Eq1[T] =
    new Eq1[T]:
      def Eq1v(x: T, y: T): Boolean =
        val ordx = s.ordinal(x)
        (s.ordinal(y) == ordx) && check(elems(ordx))(x, y)

  def Eq1Product[T](p: Mirror.ProductOf[T], elems: => List[Eq1[_]]): Eq1[T] =
    new Eq1[T]:
      def Eq1v(x: T, y: T): Boolean =
        iterator(x).zip(iterator(y)).zip(elems.iterator).forall {
          case ((x, y), elem) => check(elem)(x, y)
        }

  inline given derived[T](using m: Mirror.Of[T]): Eq1[T] =
    lazy val elemInstances = summonAll[m.MirroredElemTypes]
    inline m match
      case s: Mirror.SumOf[T]     => Eq1Sum(s, elemInstances)
      case p: Mirror.ProductOf[T] => Eq1Product(p, elemInstances)

enum Opt[+T] derives Eq1:
  case Sm(t: T)
  case Nn

@main def test(): Unit =
  import Opt.*
  val Eq1oi = summon[Eq1[Opt[Int]]]
  assert(Eq1oi.Eq1v(Sm(23), Sm(23)))
  assert(!Eq1oi.Eq1v(Sm(23), Sm(13)))
  assert(!Eq1oi.Eq1v(Sm(23), Nn))
