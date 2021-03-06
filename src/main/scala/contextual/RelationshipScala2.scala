package contextual

trait FooX

// anonymous given instanceは以下の様な命名になる
//given given_Ord_Int: Ord[Int]
//given given_Ord_List_T[T](using ord: Ord[T]): Ord[List[T]]

// implicit val scala 2
// implicit val pos: Position = tree.sourcePos

// scala 3
// val pos: Position = tree.sourcePos
// given Position = pos
