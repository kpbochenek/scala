import scala.tools.nsc.interactive.tests.InteractiveTest

object Test extends InteractiveTest {
//  override protected def loadSources(): Unit = { /* don't parse or typecheck sources */ }

  import compiler._

  override def runDefaultTests(): Unit = {
  // run test with command:
  // sbt> partest test/files/presentation/tKris
    val res = new Response[Tree]
    val pos = compiler.rangePos(sourceFiles.head, 31, 31, 44) // +

    askTypeAt(pos, res)
    res.get match {
      case Left(tree) => compiler.ask(() => reporter.println(s"$tree: ${tree.tpe}"))
      case Right(ex) => reporter.println(ex)
    }
  }
}
