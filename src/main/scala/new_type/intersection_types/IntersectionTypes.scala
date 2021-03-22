package new_type.intersection_types

trait Resettable:
  def reset(): Unit

trait Growable[T]:
  def add(t: T): Unit

def f(x: Resettable & Growable[String]) =
  x.reset()
  x.add("first")

trait A:
  def children: List[A]

trait B:
  def children: List[B]

trait C extends A,B:
  def children: List[A&B] = List.empty[A&B]
  
val x: A & B = ???
val ys: List[A & B] = x.children
