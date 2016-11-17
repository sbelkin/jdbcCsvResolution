package io.sbelkin.jdbc.csvresolution

/**
  * Created by sbelkin on 11/16/2016.
  */
import java.sql.{Connection,DriverManager}
object SelectCsv {

  def main(args: Array[String]) {
    if (args.length != 1) {
      throw new IllegalStateException("Must have length of 1 to point to directory of csv's.")
    } else {
      println("Using following path %s, as the data source.".format(args(0)))
    }
    val driver:String = "org.relique.jdbc.csv.CsvDriver"
    var connection:Connection = null
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection("jdbc:relique:csv:" + args(0))
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT id,name FROM test")
      while ( resultSet.next() ) {
        val id = resultSet.getString("id")
        val name = resultSet.getString("name")
        println("id, name = " + id + ", " + name)
      }
    } catch {
      case e => e.printStackTrace
    } finally {
      connection.close()
    }
  }
}