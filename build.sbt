lazy val commonSettings = Seq(
  version := "0.1",
  // Spark is still not stable at 2.12 after various tried.
  scalaVersion := "2.11.12"
)

val sparkVersion = "2.4.0"
val sparkGroupId = "org.apache.spark"
val sparkCore = sparkGroupId %% "spark-core" % sparkVersion
val sparkSql = sparkGroupId %% "spark-sql" % sparkVersion
val sparkML = sparkGroupId %% "spark-mllib" % sparkVersion
val sparkStream = sparkGroupId %% "spark-streaming" % sparkVersion
val spark = Seq(sparkCore, sparkSql, sparkML, sparkStream)

val scalaTestVersion = "3.0.5"
val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test

val deps = Seq(scalaTest) ++ spark

lazy val `spark-ex` = (project in file("."))
  .settings(
    commonSettings,
    libraryDependencies ++= deps
  )