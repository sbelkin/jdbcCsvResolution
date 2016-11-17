package io.sbelkin.jdbc.csvresolution

import java.sql.{Connection, DriverManager}

import com.typesafe.config.Config

/**
  * Created by sbelkin on 11/16/2016.
  */
object Mysql {

  var connection:Connection = null
  var config:Config = null

  def main(config: Config): Unit = {
    setConfig(config);
    select()
  }

  def setConfig(config: Config) : Unit = {
    this.config = config;
  }

  def connectionStart() : Unit = {
    Class.forName(config.getString("database.driverClass"))
    val url = config.getString("database.url") +"/"+ config.getString("database.database")
    connection = DriverManager.getConnection(url, config.getString("database.user"), config.getString("database.password"))
  }
  def connectionClose() : Unit = {
    connection.close()
  }

  def select(): Unit =  {
    connectionStart()
    try {
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
      connectionClose()
    }
  }
}
