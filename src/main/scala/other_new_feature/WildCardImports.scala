package other_new_feature

object WildCardImports:

  object A:
    import scala.annotation.*
    def * = ???
    def min = ???

  object B:
    import A.`*`  // imports just `*`
  
  object C:
    import A.*   // imports everything in A

  object D:
    import A.{min as minimum, `*` as multiply}
    import scala.annotation as ann
