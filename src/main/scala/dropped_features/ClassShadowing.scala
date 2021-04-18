package dropped_features

object ClassShadowing:
  class Base {
    class Ops { val a = 1 }
  }

  // class Ops cannot have the same name as class Ops in class Base -- class definitions cannot be overridden
  // というエラーになるのでリネームする
//  class Sub extends Base {
//    class Ops { val b = 2 }
//  }

  class Sub extends Base {
    class Ops2 { val b = 2 }
  }
