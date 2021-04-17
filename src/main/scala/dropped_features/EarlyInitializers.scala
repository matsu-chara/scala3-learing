package dropped_features

object EarlyInitializers:
  trait A:
    val a: Int

  // 使えないのでtrait parameterを使う
//  class B extends { val a = 1 } with A

  trait B(a: Int)

  class C extends B(1)
