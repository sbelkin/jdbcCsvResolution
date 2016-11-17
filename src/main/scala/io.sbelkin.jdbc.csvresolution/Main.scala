package io.sbelkin.jdbc.csvresolution

import java.io.File
import com.typesafe.config.{ Config, ConfigFactory }


/**
  * Created by sbelkin on 11/16/2016.
  */
object Main {
  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      throw new IllegalStateException("Must have length of 1 to point to directory of configuration.")
    } else {
      println("Using following path %s, as the data source.".format(args(0)))
    }
    val config:Config = ConfigFactory.parseFile(new File(args(0)))
    Mysql.main(config)
    Csv.main(config.getString("database.csv.filepath"))
  }
}
