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
object FixedWidthUtilityDriver {

  def lsplit(pos: List[Int], str: String): Row = {
    val (rest, result) = pos.foldLeft((str, List[String]())) {
      case ((s, res), curr) =>
        if (s.length() <= curr) {
          val split = s.substring(0).trim()
          val rest = ""
          (rest, split :: res)
        } else if (s.length() > curr) {
          val split = s.substring(0, curr).trim()
          val rest = s.substring(curr)
          (rest, split :: res)
        } else {
          val split = ""
          val rest = ""
          (rest, split :: res)
        }
    }
    Row.fromSeq(result.reverse)
  }

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C://winutils//")
    val spark = SparkSession.builder().master("yarn").config("spark.sql.warehouse.dir", "file:///C://Personalized//").getOrCreate()
    val rdd = spark.sparkContext.textFile("path of your file")
    val metadata = spark.read.option("header", "true").csv("path of your Metadata File")
    val header = metadata.select("col_name").rdd.map(x => x.getString(0).trim()).collect()
    val sizeOfColumn = metadata.select("size").rdd.map(x => x.getString(0).trim()).collect().map(_.toInt).toList
    val fields = header.map(fieldName => StructField(fieldName, StringType, nullable = true))
    val schema = StructType(fields)
    val df = spark.createDataFrame(rdd.map { x => lsplit(sizeOfColumn, x) }, schema)
    df.show(false)
  }
}