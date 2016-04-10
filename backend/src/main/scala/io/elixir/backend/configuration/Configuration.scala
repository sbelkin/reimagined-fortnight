package io.elixir.backend.configuration

import java.io.{File, FileInputStream}

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import scala.collection.mutable.ListBuffer
import scala.beans.BeanProperty

/**
  * Created by sbelkin on 3/29/2016.
  */

object Configuration {
  def load(file: String) : ConfigurationFile = {
    val input = new FileInputStream(new File(file))
    val yaml = new Yaml(new Constructor(classOf[ConfigurationFile]))
    val e = yaml.load(input).asInstanceOf[ConfigurationFile]
    e
  }
}


class ConfigurationFile {
  @BeanProperty var brewery_key: String = null

  override def toString = s"ConfigurationFile( brewery_key: $brewery_key )"
}
