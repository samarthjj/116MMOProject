package Project

import objects.Player

class Game
{
  var playerMap: Map[String, Map[String, Int]] = Map()
  /*
    update spawnPLayer with placing a player into a larger map of players
   */
  def spawnPlayer(name: String): Unit =
  {
    val random = scala.util.Random
    val x: Int = 1 + random.nextInt((99 - 1) + 1)
    val y: Int = 1 + random.nextInt((99 - 1) + 1)
      // this generates a random number between 1 and 99 (both inclusive)
    val player: Player = new Player(name,x ,y)
    val playerEntry: Map[String, Int] = Map("x" -> player.xPosition, "y" -> player.yPosition, "token" -> player.tokenAmount)
    playerMap = playerMap + (name -> playerEntry)
  }

  def move(input: String, player: Player): Unit =
  {
    val inputLower = input.toLowerCase()
    if(inputLower == "w")
      {
        player.yPosition -= 1
      }
    else if(inputLower == "a")
      {
        player.xPosition -= 1
      }
    else if(inputLower == "s")
      {
        player.yPosition += 1
      }
    else if(inputLower == "d")
      {
        player.xPosition += 1
      }
  }
}
