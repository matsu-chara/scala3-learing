package other_changed_feature

object ChangesInCompilerPlugins:
  val a = 1
  // importが通らないのでコメントアウト
//  class DivideZero extends StandardPlugin:
//    val name: String = "divideZero"
//    override val description: String = "divide zero check"
//
//    def init(options: List[String]): List[PluginPhase] =
//      (new DivideZeroPhase) :: Nil
//
//  class DivideZeroPhase extends PluginPhase:
//    import tpd.*
//
//    val phaseName = "divideZero"
//
//    override val runsAfter = Set(Pickler.name)
//    override val runsBefore = Set(Staging.name)
//
//    override def transformApply(tree: Apply)(implicit ctx: Context): Tree =
//      tree match
//        case Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0))))
//          if rcvr.tpe <:< defn.IntType =>
//          report.error("dividing by zero", tree.pos)
//        case _ =>
//          ()
//      tree

//  class DummyResearchPlugin extends ResearchPlugin:
//    val name: String = "dummy"
//    override val description: String = "dummy research plugin"
//
//    def init(options: List[String], phases: List[List[Phase]])(implicit ctx: Context): List[List[Phase]] =
//      phases
