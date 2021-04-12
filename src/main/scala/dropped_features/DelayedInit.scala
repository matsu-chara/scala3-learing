package dropped_features

object DelayedInit:
  // DelayedInitではないが使うことはできる。object intiializerの中で実行されるのでbenchrmarkとかには向かない
  object HelloWorld extends App {
    println("Hello, world!")
  }
