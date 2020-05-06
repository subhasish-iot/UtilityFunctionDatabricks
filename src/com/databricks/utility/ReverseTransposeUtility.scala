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
object ReverseTransposeUtility {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C://winutils//")
    val spark = SparkSession.builder().master("local[*]").config("spark.sql.warehouse.dir", "<warehouse dir>").getOrCreate()
    val data_df = spark.read.option("header", "true").option("delimiter", "|").option("mode", "DROPMALFORMED").csv("<src dir>")
    data_df.show(false)
    val str = "id,country,product_line_item,Product_wing,Division,region,territory,item_id,unique_id,Store_name"
    import spark.implicits._
    val mapKeys = data_df.columns.diff(str.split("\\,"))
    import org.apache.spark.sql.Dataset
    val pairs = mapKeys.map(k => Seq(lit(k), col(k))).flatten
    val mapped = data_df.select($"id", $"country", $"product_line_item", $"Product_wing", $"Division", $"region", $"territory", $"item_id", $"unique_id", $"Store_name", functions.map(pairs: _*) as "map")
    var final_df = mapped.select($"id", $"country", $"product_line_item", $"Product_wing", $"Division", $"region", $"territory", $"item_id", $"unique_id", $"Store_name", explode($"map"))
    final_df.show(false)
  }
}