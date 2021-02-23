package contextual

def max[T](x: T, y: T)(using ord: Ord[T]): T =
  if ord.compare(x, y) < 0 then y else x

// max(2, 3)(using intOrd) か max(2, 3)のようにして使う


// parameter名は省略可
def maximum[T](xs: List[T])(using Ord[T]): T =
  xs.reduceLeft(max)


// 以下のように複数のusingを使うことができる
// def f(u: Universe)(using ctx: u.Context)(using s: ctx.Symbol, k: ctx.Kind)