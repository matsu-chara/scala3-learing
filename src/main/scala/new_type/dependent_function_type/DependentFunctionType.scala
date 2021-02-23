package new_type.dependent_function_type

trait Entry:
  type Key
  val key: Key

object Entry:
  def extractKey(e: Entry): e.Key = e.key

  //  (e: Entry) => e.Key と書けるようになった
  val extractor: (e: Entry) => e.Key = extractKey

  // 値としてfを渡せる
  def foo(e: Entry)(f: (e: Entry) => e.Key): e.Key = f(e)

  // (e: Entry) => e.Keyは下記のシンタックスシュガー
  val f = new Function1[Entry, Entry#Key]:
    def apply(e: Entry): e.Key = e.key

trait C:
  type M
  val m: M

type DF = (x: C) => x.M

type IDF = (x: C) ?=> x.M

def test =
  val c = new C:
    type M = Int
    val m = 3
  
  val depfun: DF = (x: C) => x.m
  val t: Int = depfun(c)
  println(s"t=$t")   // prints "t=3"

  val idepfun: IDF = summon[C].m
  val u: Int = idepfun(using c)
  println(s"u=$u")   // prints "u=3"

trait Effect

// Type X => Y
abstract class EffFun[-X, +Y]:
  type Eff <: Effect
  def apply(x: X): Eff ?=> Y

class CanThrow extends Effect
class CanIO extends Effect

class IntToString extends EffFun[Int, String]:
  type Eff = CanThrow
  def apply(x: Int) = x.toString

class StringToInt extends EffFun[String, Int]:
  type Eff = CanIO
  def apply(x: String) = x.length

def map[A, B](f: EffFun[A, B])(xs: List[A]): f.Eff ?=> List[B] = 
  xs.map(f.apply)

def mapFn[A, B]: (f: EffFun[A, B]) => List[A] => f.Eff ?=> List[B] = 
  f => xs => map(f)(xs)

def compose[A, B, C](f: EffFun[A, B])(g: EffFun[B, C])(x: A):
f.Eff ?=> g.Eff ?=> C =
  g(f(x))

def composeFn[A, B, C]:
(f: EffFun[A, B]) => (g: EffFun[B, C]) => A => f.Eff ?=> g.Eff ?=> C =
  f => g => x => compose(f)(g)(x)

object DepFunTest:
  @main def test =
    val i2s = new IntToString
    val s2i = new StringToInt

    given ct: CanThrow = new CanThrow
    given ci: CanIO = new CanIO

    assert(map(i2s)(List(1, 2, 3)).mkString == "123")
    assert(compose(i2s)(s2i)(22) == 2)