package com.databricks.utility

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{ lit, col, concat_ws, split, struct }
import org.apache.spark.sql.catalyst.expressions.Explode
import org.apache.spark.sql.types.{ StructType, StructField, StringType };
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.AnalysisException
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.LongType
import org.apache.spark.sql.functions.{ lower, upper }
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.functions.{ input_file_name, regexp_extract }
import org.apache.spark.sql.functions._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
object DynamicTransposeUtility {
  def dataValidator(map_val: Seq[Map[String, String]], rule: String): String = {
    try {
      val rule_array = rule.split("#!").toList
      val src_map = map_val.toList.flatten.toMap
      var output_str = ""
      rule_array.foreach(f =>
        output_str = output_str + "!" + src_map.getOrElse(f, "#"))
      return output_str.drop(1)
    } catch {
      case t:
        Throwable =>
        t.printStackTrace().toString()
        return "0".toString()
    }
  }

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").config("spark.sql.warehouse.dir", "<src dir>").getOrCreate()
    val data_df = spark.read.option("header", "true").csv("<data path src>")
    val lkp_df = spark.read.option("header", "true").csv("lookup path source>")
    import spark.implicits._
    import org.apache.spark.sql.functions.broadcast
    val lkp_df_brdcast = broadcast(lkp_df)
    val result_df = data_df.join(broadcast(lkp_df_brdcast), $"idoc_qualifier_org" === $"qualifier", "inner")
    val df1 = result_df.groupBy(col("idoc_number"), col("orderid")).agg(collect_list(map($"desc", $"idoc_org")) as "map")
    import org.apache.spark.sql.functions.udf
    import org.apache.spark.sql.functions.{
      lit,
      max,
      row_number
    }
    import spark.implicits._
    import org.apache.spark.sql.Row
    val map_val = lkp_df.rdd.map(row => row.getString(1)).collect().mkString("#!")
    spark.sparkContext.broadcast(map_val)
    val recdValidator = udf(dataValidator _)
    var latest_df = df1.withColumn("explode_out", split(recdValidator(df1("map"), lit(map_val)), "!")).drop("map")
    val columns = map_val.split("#!").toList
    latest_df = columns.zipWithIndex.foldLeft(latest_df) {
      (memodDF, column) =>
        {
          memodDF.withColumn(column._1, col("explode_out")(column._2))
        }
    }
      .drop("explode_out")
    latest_df.show()
  }

}