package io.elixir.data.mysql

/**
  * Created by sbelkin on 10/08/2016.
  */
import java.sql.{Connection, DriverManager}

import org.apache.commons.dbcp2.BasicDataSource

object Datasource {
  val url = "jdbc:mysql://localhost:3306/jpadb"
  val driver = "com.mysql.jdbc.Driver"
  val username = "root"
  val password = "root"
  val connectionPool = new BasicDataSource()
  try {
    connectionPool.setUsername(username)
    connectionPool.setPassword(password)
  } catch {
    case e: Exception => e.printStackTrace
  }
  connectionPool.setDriverClassName(driver);
  connectionPool.setUrl(url)
  connectionPool.setInitialSize(5)
}
