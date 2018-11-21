package sparkmlex

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("SparkML WordCount")
    val sc = new SparkContext("local", "SparkML WordCount")

    val input = sc.textFile("README.md")
    val words = input.flatMap(line => line.split(" "))
    val counts = words.map(word => (word, 1)).reduceByKey(_ + _)
    counts.saveAsTextFile("counts")
  }
}
