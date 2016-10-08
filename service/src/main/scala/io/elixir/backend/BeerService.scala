package io.elixir.backend

import akka.actor._
import akka.util.Timeout
import spray.can.Http
import spray.can.Http.RegisterChunkHandler
import spray.can.server.Stats
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.http._
import spray.util._

import scala.concurrent.duration._

class BeerService extends Actor with ActorLogging {
  implicit val timeout: Timeout = 1.second // for the actor 'asks'
  import context.dispatcher // ExecutionContext for the futures and scheduler

  def receive = {
    case _: Http.Connected => sender ! Http.Register(self)

    case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
      sender ! index

    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
      sender ! HttpResponse(entity = "PONG!")

    case HttpRequest(GET, Uri.Path("/beer"), _, _, _) =>
      sender ! HttpResponse(entity = "Beer")

//    case HttpRequest(POST, Uri.Path("/beer"), _, entity, _) =>
//    case HttpRequest(POST, Uri.Path("/beer"), entity = HttpEntity(ContentTypes.`text/plain`, )) =>
//      println("here")
//      println(entity)
//      sender ! HttpResponse(entity)

  }

  ////////////// helpers //////////////

  lazy val index = HttpResponse(
    entity = HttpEntity(`text/html`,
      <html>
        <body>
          <h1>Say hello to <i>spray-can</i>!</h1>
          <p>Defined resources:</p>
          <ul>
            <li><a href="/ping">/ping</a></li>
            <li><a href="/beer">/beer</a></li>
          </ul>
        </body>
      </html>.toString()
    )
  )
}