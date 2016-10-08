package io.elixir.entities

import javax.persistence.{EntityManager, EntityManagerFactory, Persistence}

/**
  * Created by sbelkin on 10/08/2016.
  */
import java.sql.{Connection,DriverManager}

object Connection {
  def main(args: Array[String]): Unit = {

    val url = "jdbc:mysql://localhost:3306/jpadb"
    val driver = "com.mysql.jdbc.Driver"
    val username = "root"
    val password = "root"
    var connection: Connection = null
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      val rs = statement.executeQuery("SELECT * FROM beer ")
      while (rs.next) {
        val id = rs.getString("id")
        val name = rs.getString("name")
        val extra = rs.getString("extra")
        println("id = %s, name = %s, extra = %s".format(id,name,extra))
      }
    } catch {
      case e: Exception => e.printStackTrace
    }
    connection.close
  }
}
