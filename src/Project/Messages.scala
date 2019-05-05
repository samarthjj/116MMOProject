package Project

case class AddPlayer(name: String)
case class GameState(state: String)
case object Send
case class RemovePlayer(name: String)