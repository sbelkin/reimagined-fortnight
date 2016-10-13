package io.elixir.data.memory

import io.elixir.data.shared.Initialize
import scala.collection.mutable
import org.yaml.snakeyaml.Yaml

/**
  * Created by sbelkin on 10/9/2016.
  */
class IntializeMap extends Initialize {

  val map = new mutable.HashMap[Int,String]

  override def connect(yaml: Yaml): Unit = {
  }
}
