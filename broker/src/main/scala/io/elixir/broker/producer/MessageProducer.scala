package io.elixir.broker.producer

/**
  * Created by sbelkin on 5/22/2016.
  */

import akka.actor.ActorSystem
import akka.camel.CamelExtension
import io.elixir.broker.consumer.route.QueueNames

object MessageProducer extends App {

  val system = ActorSystem("some-system")
  val camel = CamelExtension(system)
  val camelContext = camel.context
  val producerTemplate = camel.template
  producerTemplate.sendBody(QueueNames.fooBar, "Message to Foo Bar")

}