lazy val commonSettings = Seq(
  version := "0.1",
  // Spark is still not stable at 2.12 after various tried.
  scalaVersion := "2.11.12"
)

val sparkVersion = "2.4.0"
val sparkCore = "org.apache.spark" % "spark-core_2.11" % sparkVersion

lazy val SparkMLEx = (project in file("."))
  .settings(
    commonSettings,
    libraryDependencies += sparkCore
  )