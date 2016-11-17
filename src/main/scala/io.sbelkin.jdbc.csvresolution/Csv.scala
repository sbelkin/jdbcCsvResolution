package io.sbelkin.jdbc.csvresolution

/**
  * Created by sbelkin on 11/16/2016.
  */
import java.sql.{Connection, DriverManager}

import com.typesafe.config.Config
object Csv {

  var connection:Connection = null
  var path: String = null

  def main(path:String) : Unit = {
    setPath(path)
    select()
  }

  def setPath(path:String): Unit ={
    this.path = path
  }

  def connectionStart() : Unit = {
    if (path.length == 0) {
      throw new IllegalStateException("Must have a path for CSV Jdbc set.")
    } else {
      println("Using following path %s, as the data source.".format(path))
    }
    val driver:String = "org.relique.jdbc.csv.CsvDriver"
    Class.forName(driver)
    connection = DriverManager.getConnection("jdbc:relique:csv:" + path)
  }

  def connectionEnd() : Unit = {
    connection.close()
  }

  def select() : Unit = {
    connectionStart()
    try {
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
      connectionEnd()
    }
  }
}