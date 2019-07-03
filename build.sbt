
name := "CdnSite"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "com.github.pathikrit" %% "better-files" % "3.8.0",
  "com.github.xaanit" % "Scalalin" % "db1a80d",
  "commons-io" % "commons-io" % "2.6"
)

mainClass in assembly := Some("it.xaan.cdn.Main")
assemblyJarName in assembly := "cdn.jar"
assemblyMergeStrategy in assembly := {
  case PathList("org", "eclipse", "jetty", "http", "encoding.properties") => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}