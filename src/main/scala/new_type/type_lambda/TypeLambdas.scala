package new_type

type Foo = [T, U] =>> Map[U, T]
type Bar = [T >: String <: Any, U] =>> Map[U, T]

// 以下２つは同じ
type T[X] = List[X]
type TT = [X] =>> List[X]

// 以下はcovariantを宣言できないので同じではない（一回type lambdaに展開されたあとにvarianceのチェックが行われる）
//type F2[A, +B] = A => B
//type F2 = [A, +B] =>> A => B //  no `+/-` variance annotation allowed here

// 以下はinvariant
opaque type O[X] = List[X]

// 以下はcovariant
type O2[X] = List[X]
