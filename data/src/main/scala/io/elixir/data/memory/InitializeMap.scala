package io.elixir.data.memory

import io.elixir.data.shared.Initialize
import io.elixir.entities.Beer

import scala.collection.mutable.{HashMap, MultiMap, Set}
import org.yaml.snakeyaml.Yaml

/**
  * Created by sbelkin on 10/9/2016.
  */
class InitializeMap extends Initialize {

  val map: HashMap[Int,Set[Beer]] = new HashMap[Int, Set[Beer]] with MultiMap[Int, Beer]

  override def connect(yaml: Yaml): Unit = {
  }
}
