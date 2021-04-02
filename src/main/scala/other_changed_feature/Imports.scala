package other_changed_feature

object Imports:

  object A:
    import scala.annotation.*
    def * = ???
    def min = ???

  object B:
    import A.`*`  // imports just `*`
  
  object C:
    import A.*   // imports everything in A

  object D:
    import A.{`*`, min}

    import scala.annotation as ann
