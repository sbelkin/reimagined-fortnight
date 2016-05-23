package io.elixir.broker.consumer.route

/**
  * Created by sbelkin on 5/22/2016.
  */
import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.Session
import akka.camel.{CamelMessage, Consumer}

class TestFooBar extends Consumer {
  def endpointUri = QueueNames.fooBar

  def receive = {
    case msg: CamelMessage => println("Send %s" format msg.bodyAs[String])
  }
}