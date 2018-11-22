package sparkmlex

import org.apache.hadoop.mapred.FileAlreadyExistsException
import org.apache.spark.sql.SparkSession

object WordCount {
  def main(args: Array[String]): Unit = {
    // Since DataFrame API, directly usage of SparkContext and SparkConf being reduced.
    val spark = SparkSession.builder().appName("Spark WordCount Application").getOrCreate()

    val input = spark.sparkContext.textFile("README.md")
    val words = input.flatMap(line => line.split(" "))
    val counts = words.map(word => (word, 1)).reduceByKey(_ + _)

    try {
      counts.saveAsTextFile("counts")
    } catch {
      case ex: FileAlreadyExistsException => println(s"File already existed, but ignore this ... => ${ex.getMessage}")
    }
  }
}
