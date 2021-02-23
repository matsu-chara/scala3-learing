// def f[T: C1 : C2, U: C3](x: T)(using y: U, z: V): R
// は以下に展開される
// def f[T, U](x: T)(using y: U, z: V)(using C1[T], C2[T], C3[U]): R

