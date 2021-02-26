package contextual

import scala.language.strictEquality

class ListA[+T]:
//  def contains(x: Any): Boolean <- なんでも入る
//  def contains(x: T): Boolean  <- コンパイルエラー
//  def contains[U >: T](x: U): Boolean <- Anyを許容してしまう
  def contains[U >: T](x: U)(using CanEqual[T, U]): Boolean = ???
