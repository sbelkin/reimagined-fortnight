package io.elixir.backend

import spray.can.Http.CloseAll
import spray.httpx.SprayJsonSupport
import spray.httpx.unmarshalling._
import spray.httpx.unmarshalling.FromResponseUnmarshaller
import spray.json
import spray.json.DefaultJsonProtocol
import scala.concurrent.Future
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory
import scala.collection.concurrent.Map
import scala.collection.mutable.ArrayBuffer
import akka.actor._
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http
import spray.http._
import spray.json._
import HttpCharsets._
import MediaTypes._
import spray.http.HttpHeaders._
import spray.http.HttpMethods._
import akka.util.Timeout
import spray.json.DefaultJsonProtocol
import spray.httpx.SprayJsonSupport
import java.io.File
//import scala.util.parsing.json._


//MESSAGES

//UserSpawner's messages
case class StartSpawning()

//User's Creation Messages
case class createProfile(name:String,gender:String,location:String,birthday:String)
case class createPost(postContent:String)
case class uploadPicture(userName:String)
case class uploadAlbum(userName:String)

//User's read messages
case class viewProfile(userName:String)
case class viewTimeline(userName:String)
case class viewPhoto(userName:String)
case class viewAlbum(userName:String)
case class viewFriendList(userName:String)

// User's other messages
case class sendFriendRequest(userName:String)

//Json Conversion Formats for DataStructures.
object JsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val UserFormat = jsonFormat2(UserDataStructure)
  implicit val ProfileFormat=jsonFormat4(Profile)
  implicit val PostFormat=jsonFormat1(Post)
}
object Globs
{
  var ctr:Integer=0
  var port:Integer=7200
}
//Data Structures Definitions
case class UserDataStructure(userName:String,password:String)
{
  import JsonSupport._
  def makeJsonString():String=
  {
    return this.toJson.toString()
  }
}
case class Profile(name:String,gender:String,location:String,birthday:String)
{
  import JsonSupport._
  def makeJsonString():String=
  {
    return this.toJson.toString()
  }
}
case class Post(postContent:String)
{
  import JsonSupport._
  def makeJsonString():String=
  {
    return this.toJson.toString()
  }
}
case class Picture(picture:File)
{
  def getBytes(): Array[Byte] =
  {
    return this.getBytes()
  }

}
case class Album(Album:ArrayBuffer[Picture])
{
  def getBytes(): Array[Byte] =
  {
    return this.getBytes()
  }
}


//Actors Bottom Up
class User(userName:String,password:String) extends Actor with ActorLogging {

  import context._
  implicit val timeout: Timeout = 300.seconds
  var serverAddress = "192.168.0.16:"

  override def preStart() = {
    import Globs._
    val newUser = new UserDataStructure(userName, password)
    ctr+=1
    port=7200+ctr%10

    var portval:String=port.toString()
    serverAddress+=portval
    // POST REQUEST WITH NEW USER REGISTRATION DETAILS.
    // PATH : http://192.168.0.24:port/NewUserRegistration
    type FromResponseUnmarshaller[UserDataStructure] = Deserializer[HttpResponse, UserDataStructure]
    for {

    //val pipeline = sendReceive~> unmarshall[String]
      response <- IO(Http).ask(HttpRequest(POST, Uri( s"http://$serverAddress/newUserRegistration"), entity = HttpEntity(`application/json`, newUser.makeJsonString()))).mapTo[HttpResponse]
    //_ <- IO(Http) ? CloseAll
    } yield {
      val jsonResponse= (response.entity.asString).parseJson
      //println(response)
      println(jsonResponse)
      /* if(response.status.toString=="200")
          {
            for{
            response_profile<- IO(Http).ask(HttpRequest(GET, Uri(s"http://$serverAddress/CreateProfile"))).mapTo[HttpResponse]
            } yield
              {
                val profileresponse=response_profile.status.toString()
                log.debug(s"$profileresponse came out okay")
              }
            }
      }*/
    }
  }

  def receive = {

    //Request to create a post.
    //Path example : http://192.168.0.16:1920/user/shivak/posts/createPost
    case createPost(userName: String) => {
      val newPost = Post("Hey! its thanksgiving and I'm here coding stuff :(")
      for {
        response <- IO(Http).ask(HttpRequest(POST, Uri(s"http://$serverAddress/user/$userName/posts/createPost"), entity = HttpEntity(`application/json`, newPost.makeJsonString())))
      } yield {
        println(response)
      }
    }

    //Sending a friend Request
    //Path example : http://192.168.0.16:1920/user/shivak/sendFriendRequest
    case sendFriendRequest(userName: String) => {
      for {
        response <- IO(Http).ask(HttpRequest(GET, Uri(s"http://$serverAddress/user/$userName/sendFriendRequest")))
      } yield {
        println(response)
      }

    }

    //Uploading an album
    //Path example : http://192.168.0.16:1920/user/shivak/albums/uploadAlbum
    case uploadAlbum(userName: String) => {
      val picFile = new File("picfile.jpg")
      val picture = new Picture(picFile)
      val albumobject = ArrayBuffer[Picture](picture)
      val album = new Album(albumobject)

      for {
        response <- IO(Http).ask(HttpRequest(POST, Uri(s"http://$serverAddress/user/$userName/albums/uploadAlbum"), entity = HttpEntity(`image/jpeg`, album.getBytes())))
      } yield {
        println(response)
      }
    }

    //Uploading a picture
    //Path example : http://192.168.0.16:1920/user/shivak/photos/uploadPicture
    case uploadPicture(userName: String) => {
      val picFile = new File("picfile.jpg")
      val picture = Picture(picFile)
      for {
        response <- IO(Http).ask(HttpRequest(POST, Uri(s"http://$serverAddress/$userName/photos/uploadPhoto"), entity = HttpEntity(`image/jpeg`, picture.getBytes())))
      } yield {
        println(response)
      }
    }

    //Viewing a user's timeline
    //Path example : http://192.168.0.16:1920/user/shivak/timeline
    case viewTimeline(userName: String) => {
      for {
        response <- IO(Http).ask(HttpRequest(GET, Uri(s"http://$serverAddress/user/$userName/posts/timeline")))
      } yield {
        println(response)
      }
    }
    //Viewing a user's profile
    //Path example : http://192.168.0.16:1920/user/shivak/profile/
    case viewProfile(userName: String) => {
      for {
        response <- IO(Http).ask(HttpRequest(GET, Uri(s"http://$serverAddress/user/$userName/profile")))
      } yield {
        println(response)
      }
    }
    //Viewing a user's picture
    //Path example : http://192.168.0.16:1920/user/shivak/photos/viewPhoto
    case viewPhoto(userName: String) => {
      for {
        response <- IO(Http).ask(HttpRequest(GET, Uri(s"http://$serverAddress/user/$userName/photos/viewPhoto")))
      } yield {
        println(response)
      }
    }
    //Viewing a user's album
    //Path example : http://192.168.0.16:1920/user/shivak/photos/viewPhoto
//    case viewPhoto(userName: String) => {
//      for {
//        response <- IO(Http).ask(HttpRequest(GET, Uri(s"http://$serverAddress/user/$userName/albums/viewAlbum")))
//      } yield {
//        println(response)
//      }
//    }
    //Viewing a user's friendlist
    //Path example : http://192.168.0.16:1920/user/shivak/friends
    case viewFriendList(userName: String) => {
      for {
        response <- IO(Http).ask(HttpRequest(GET, Uri(s"http://$serverAddress/user/$userName/friends")))
      } yield {
        println(response)
      }
    }

    case _ => {}
  }
}

class UserSpawner extends Actor {
  import context._

  def receive =
  {
    case StartSpawning =>
    {
      for( i <-0 to 100 )
      {
        val UserActor = context.actorOf(Props(new User("user"+i+"@dos.com","badpassword")))
      }
    }
    case _ =>
    {

    }
  }

}