package io.elixir.broker

/**
  * Created by sbelkin on 5/22/2016.
  */
import javax.jms._

import org.apache.activemq.ActiveMQConnectionFactory

object MessageConsumer {
  val activeMqUrl: String = "tcp://localhost:61616"
  val queueName: String = "TEST.FOO"

  def main(args: Array[String]) {
    val connectionFactory = new ActiveMQConnectionFactory(activeMqUrl)
    val connection = connectionFactory.createConnection
    connection.start

    val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val destination = session.createQueue(queueName)
    val consumer: MessageConsumer = session.createConsumer(destination)

    val listener: MessageListener = new MessageListener {
      override def onMessage(message: Message): Unit = {
        try {
          if (message.isInstanceOf[TextMessage]) {
            val textMessage: TextMessage = message.asInstanceOf[TextMessage]
            println(textMessage.getText + " received @ " + System.currentTimeMillis())
            message.acknowledge
          }
        } catch {
          case je: JMSException => {
            println(je.getMessage)
          }
        }
      }
    }

    consumer.setMessageListener(listener)
  }
}

