package io.elixir.broker.consumer

/**
  * Created by sbelkin on 5/22/2016.
  */
import akka.actor._
import akka.camel.{CamelExtension, CamelMessage, Consumer}
import io.elixir.broker.consumer.route.{TestFooBar, TestFooFoo}
import org.apache.activemq.camel.component.ActiveMQComponent

object MessageConsumer extends App {
  val system = ActorSystem("AkkaProjectInScala")
  val camel = CamelExtension(system)
  val camelContext = camel.context
  camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent(
    "tcp://0.0.0.0:61616"))

  val consumerFooFoo = system.actorOf(Props[TestFooFoo])
  val consumerFooBar = system.actorOf(Props[TestFooBar])
}

