package Project

import akka.actor.Actor

class TheActor extends Actor {

  val game: Game = new Game

  override def receive: Receive = {
    case player: AddPlayer =>
      game.spawnPlayer(player.name)
    case remove: RemovePlayer =>
      game.removePlayer(remove.name)
    case move: MovePlayer =>
      val player: Player = game.playerMap(move.name)
      game.move(move.key, player)
    case Send =>
      sender() ! GameState(game.gameState())
  }

}
