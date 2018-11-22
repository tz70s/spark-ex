lazy val commonSettings = Seq(
  version := "0.1",
  // Spark is still not stable at 2.12 after various tried.
  scalaVersion := "2.11.12"
)

val sparkVersion = "2.4.0"
val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion
val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion
val sparkML = "org.apache.spark" %% "spark-mllib" % sparkVersion
val spark = Seq(sparkCore, sparkSql, sparkML)

val scalaTestVersion = "3.0.5"
val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test

val deps = Seq(scalaTest) ++ spark

lazy val SparkMLEx = (project in file("."))
  .settings(
    commonSettings,
    libraryDependencies ++= deps
  )