package sparkmlex

import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class WordCountSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  lazy val spark = SparkSession.builder().master("local").appName("Spark Test").getOrCreate()

  override def afterAll(): Unit =
    spark.stop()

  behavior of "Word Count"

  it should "calculate the right word counts" in {
    val input = spark.sparkContext.parallelize(Seq("a a b b"))
    val counts = input.flatMap(s => s.split(" ")).map(word => (word, 1)).reduceByKey(_ + _).collectAsMap()

    counts should be(Map("a" -> 2, "b" -> 2))
  }
}
