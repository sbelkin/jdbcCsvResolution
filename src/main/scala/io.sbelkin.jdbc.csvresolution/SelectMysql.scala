package io.sbelkin.jdbc.csvresolution

import java.sql.{Connection, DriverManager}

/**
  * Created by sbelkin on 11/16/2016.
  */
object SelectMysql {
  def main(args: Array[String]) {
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost/mysql"
    val username = "root"
    val password = "root"
    var connection:Connection = null
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT host, user FROM user")
      while ( resultSet.next() ) {
        val host = resultSet.getString("host")
        val user = resultSet.getString("user")
        println("host, user = " + host + ", " + user)
      }
    } catch {
      case e => e.printStackTrace
    } finally {
      connection.close()
    }
  }
}
