import sbt.Keys.scalacOptions

object BuildHelper {

//  private val versions: String => String = {
//    import org.snakeyaml.engine.v2.api.{Load, LoadSettings}
//
//    import java.util.{List => JList, Map => JMap}
//    import scala.jdk.CollectionConverters._
//
//    val doc = new Load(LoadSettings.builder().build())
//      .loadFromReader(scala.io.Source.fromFile(".github/workflows/ci.yml").bufferedReader())
//    val yaml = doc.asInstanceOf[JMap[String, JMap[String, JMap[String, JMap[String, JMap[String, JList[String]]]]]]]
//    val list = yaml.get("jobs").get("test").get("strategy").get("matrix").get("scala").asScala
//    val map = list.map(v => (v.split('.').take(2).mkString("."), v)).toMap
//
//    (prefix: String) => map.find(_._1.startsWith(prefix)).map(_._2).get
//  }
//
//  val ScalaDotty: String                 = versions("3")

  def macroDefinitionSettings = Seq(
    scalacOptions += "-language:experimental.macros",
//    libraryDependencies ++= {
//      if (scalaVersion.value == ScalaDotty) Seq()
//      else
//        Seq(
//          "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided",
//          "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided"
//        )
//    }
  )

}
