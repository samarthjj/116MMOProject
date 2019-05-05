package Project

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case class AddPlayer(name: String)
case class GameState(state: String)
case object Send

class TheActor extends Actor {

  val game: Game = new Game

  override def receive: Receive = {
    case player: AddPlayer =>
      game.spawnPlayer(player.name)

    case Send =>
      sender() ! GameState(game.gameState())
  }

}
