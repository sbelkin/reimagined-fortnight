package io.elixir.data.mysql

import java.sql.Connection

/**
  * Created by sbelkin on 10/26/2016.
  */
object SqlSelect {
  val connection = Datasource.connectionPool.getConnection
  val statement = connection.createStatement
  val rs = statement.executeQuery("SELECT * FROM beer ")
  while (rs.next) {
    val id = rs.getString("id")
    val name = rs.getString("name")
    val extra = rs.getString("extra")
    println("id = %s, name = %s, extra = %s".format(id,name,extra))
  }
}
