package new_type.match_types

type Elem[X] = X match
  case String => Char
  case Array[t] => t
  case Iterable[t] => t

def typed[A](a: A): Unit = ()

object Check:
  typed[Elem[String]]('c')
  typed[Char](null.asInstanceOf[Elem[String]])
//  typed[Elem[Int]]('c') // Required: new_type.match_types.Elem[Int]
//  typed[Int](null.asInstanceOf[Elem[Int]]) // Required: Int

// 再帰バージョン
type LeafElem[X] = X match
  case String => Char
  case Array[t] => LeafElem[t]
  case Iterable[t] => LeafElem[t]
  case AnyVal => X

type Concat[Xs <: Tuple, +Ys <: Tuple] <: Tuple = Xs match
  case EmptyTuple => Ys
  case x *: xs => x *: Concat[xs, Ys]

object Check2:
  typed[Concat[(Int, Int), (String, Int)]]((1, 2, "3", 4))

// 型レベルソートの例
// https://xuwei-k.hatenablog.com/entry/2021/02/21/153451

// depdendent typedなメソッド定義に使える
// ただし
// ガードがある場合は使えない
// マッチしている型はscrutineeの型（かそのサブタイプ）でないとだめ
// マッチ式とマッチタイプは同じケース数でないとだめ
// パターンがすべてtype pattern であり、全部対応する型に対して=:=でないとだめ
def leafElem[X](x: X): LeafElem[X] = x match
  case x: String      => x.charAt(0)
  case x: Array[t]    => leafElem(x(9))
  case x: Iterable[t] => leafElem(x.head)
  case x: AnyVal      => x


// 無限ループ検知
type L[X] = X match
  case Int => L[X]

object Check3:
  def g[X]: L[X] = ???
//  val x: Int = g[Int] // Recursion limit exceeded.