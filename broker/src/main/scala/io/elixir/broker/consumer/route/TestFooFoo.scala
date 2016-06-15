package io.elixir.broker.consumer.route

import akka.actor.Actor
import akka.camel.{CamelMessage, Consumer}

/**
  * Created by sbelkin on 5/22/2016.
  */
class TestFooFoo extends Actor with Consumer {
  def endpointUri = QueueNames.fooFoo

  def receive = {
    case msg: CamelMessage => {
      var value = msg.bodyAs[String]
    }
  }
}
