package io.elixir.data.shared

import org.yaml.snakeyaml.Yaml

/**
  * Created by sbelkin on 10/9/2016.
  */
abstract class Initialize {
  def connect(yaml: Yaml)
}