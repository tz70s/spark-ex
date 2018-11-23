package sparkex.stream

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming._

object StreamWordCount {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Streaming based Word Count").getOrCreate()
    val ssc = new StreamingContext(spark.sparkContext, Seconds(1))

    val lines = ssc.socketTextStream("localhost", 9999)
    val words = lines.flatMap(_.split(" "))
    val counts = words.map((_, 1)).reduceByKey(_ + _)

    // This operation from DStream is dealing with `take` operation, with 10 number by default.
    counts.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
