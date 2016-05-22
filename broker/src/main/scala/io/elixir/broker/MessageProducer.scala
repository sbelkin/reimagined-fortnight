package io.elixir.broker

/**
  * Created by sbelkin on 5/22/2016.
  */
import javax.jms.{MessageProducer, Session, TextMessage}

import org.apache.activemq.{ActiveMQConnectionFactory, ScheduledMessage}

object MessageProducer {

  val activeMqUrl: String = "tcp://localhost:61616"
    val queueName: String = "TEST.FOO"

    def main(args: Array[String]) {
      val connectionFactory = new ActiveMQConnectionFactory(activeMqUrl)
      val connection = connectionFactory.createConnection
      connection.start

      val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
      val destination = session.createQueue(queueName)
      val messageProducer: MessageProducer = session.createProducer(destination)

      for (i <- 0 until 10) {
        val text: String = "Message id: " + i + " sent @ " + System.currentTimeMillis
        val textMessage: TextMessage = session.createTextMessage(text)
        val deliverAfterPeriod: Long = i * 30000;
        textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, deliverAfterPeriod)
        messageProducer.send(textMessage)
        println(textMessage.getText)
      }

      connection.close()
    }
  }
