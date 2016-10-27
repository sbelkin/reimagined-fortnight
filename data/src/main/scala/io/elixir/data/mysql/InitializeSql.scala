package io.elixir.data.mysql

import io.elixir.data.shared.Initialize
import org.yaml.snakeyaml.Yaml

/**
  * Created by sbelkin on 10/26/2016.
  */
class InitializeSql extends Initialize{
  override def connect(yaml: Yaml): Unit = {
    val connection = Datasource.connectionPool.getConnection
    connection.getMetaData
  }
}
