package contextual

import java.util.concurrent.ForkJoinPool
import scala.concurrent.ExecutionContext
import scala.collection.mutable.ArrayBuffer

type Executable[T] = ExecutionContext ?=> T

given ec: ExecutionContext = ExecutionContext.fromExecutor(new ForkJoinPool())

def f(x: Int): ExecutionContext ?=> Int = 1

class Table:
 val rows = new ArrayBuffer[Row]

  def add(r: Row): Unit =
    rows += r

  override def toString =
    rows.mkString("Table(", ",", ")")

class Row:
  val cells = new ArrayBuffer[Cell]

  def add(c: Cell): Unit =
    cells += c

  override def toString =
    cells.mkString("Row(", ", ", ")")

case class Cell(elem: String)

def table(init: Table ?=> Unit) =
  given t: Table = Table()
  init
  t

def row(init: Row ?=> Unit)(using t: Table) =
  given r: Row = Row()
  init
  t.add(r)

def cell(str: String)(using r: Row) =
  r.add(new Cell(str))


def set(): Table =
  table {
    row {
      cell("top left")
      cell("top right")
    }
    row {
      cell("bottom left")
      cell("bottom right")
    }
  }

object PostConditions:
  opaque type WrappedResult[T] = T

  def result[T](using r: WrappedResult[T]): T = r // WrappedResult[T] ?=> Tという型

  extension [T](x: T)
    def ensuring(condition: WrappedResult[T] ?=> Boolean): T =
      assert(condition(using x))
      x

def check =
  import PostConditions.{ensuring, result}
  val s = List(1, 2, 3).sum.ensuring(result == 6)
  // ...ensuring($x ?=> result(using $x) == 6)
