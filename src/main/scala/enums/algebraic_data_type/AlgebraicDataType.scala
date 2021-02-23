package enums.algebraic_data_type

enum Option[+T]:
  case Some(x: T)
  case None //  extends Option[Nothing] として扱われる


// varianceは引き継がれるのでReflはcontravariantなポジションにTが出てきてしまうのでコンパイルエラーになる
//enum View[-T]:
//  case Refl(f: T => T)
