package other_new_feature

object TransparentTrait:
  trait Kind
  case object Var extends Kind
  case object Val extends Kind
  val x: Set[Kind & Product & Serializable] = Set(if true then Val else Var)

  transparent trait S
  trait KindX
  object VarX extends KindX, S
  object ValX extends KindX, S
  val xX: Set[KindX] = Set(if true then ValX else VarX) // NoS 
