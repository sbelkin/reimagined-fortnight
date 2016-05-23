package io.elixir.broker.producer

/**
  * Created by sbelkin on 5/22/2016.
  */
import akka.pattern.ask

import akka.actor.{Actor, ActorSystem, Props}
import akka.camel.{CamelExtension, CamelMessage, Producer}
import io.elixir.broker.consumer.route.QueueNames

class ProducerSender(uri: String) extends Actor with Producer {
  def endpointUri = uri
}

object MessageProducer extends App {

  val system = ActorSystem("some-system")
  val producer = system.actorOf(Props(classOf[ProducerSender], QueueNames.fooBar))

  producer ! CamelMessage("bar", Map(CamelMessage.MessageExchangeId -> "123"))

}