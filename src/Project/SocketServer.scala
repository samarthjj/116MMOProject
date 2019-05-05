package Project

import java.net.InetSocketAddress
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString
import play.api.libs.json.{JsValue, Json}

class SocketServer(gameActor: ActorRef) extends TheActor {

  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 8000))

  var server: ActorRef = _
  var buffer: String = ""

  override def receive: Receive = {
    case b: Bound => println("Listening on port: " + b.localAddress.getPort)

    case c: Connected =>
      this.server = sender()
      sender() ! Register(self)

    case PeerClosed =>
      this.server = _

    case r: Received =>
      buffer += r.data.utf8String
      while (buffer.contains("~")) {
        val curr = buffer.substring(0, buffer.indexOf("~"))
        buffer = buffer.substring(buffer.indexOf("~") + 1)
        handle(curr)
      }

    case Send =>
      gameActor ! Send

    case gs: GameState =>
      server ! Write(ByteString(gs.state + "~"))
  }


  def handle(messageString: String):Unit = {
    val message: JsValue = Json.parse(messageString)
    val username = (message \ "username").as[String]
    val messageType = (message \ "action").as[String]

    messageType match {
      case "connected" => gameActor ! AddPlayer(username)
      case "disconnected" => gameActor ! RemovePlayer(username)
      case "move" =>
        val x = (message \ "x").as[Double]
        val y = (message \ "y").as[Double]
        gameActor ! MovePlayer(username, x, y)
    }
  }

}


object TCPSocketServer {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem()

    import actorSystem.dispatcher

    import scala.concurrent.duration._

    val theActor = actorSystem.actorOf(Props(classOf[TheActor]))
    val server = actorSystem.actorOf(Props(classOf[SocketServer], theActor))

    actorSystem.scheduler.schedule(16.milliseconds, 32.milliseconds, theActor, UpdateGame)
    actorSystem.scheduler.schedule(32.milliseconds, 32.milliseconds, server, SendGameState)
  }

}
