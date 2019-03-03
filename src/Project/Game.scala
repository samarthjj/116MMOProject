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

  def hitdetection(player1: Player ,player2: Player): Unit = {
    if(player1.xPosition == player2.xPosition && player1.yPosition == player2.yPosition){
      fight(player1, player2)
    }
  }

  def kill(player: Player): Unit = {
    if(databasemap.keySet.contains(player)){

    }
  }

  def fight(player1: Player, player2: Player): Unit = {
    val random = scala.util.Random
    if(player1.tokenAmount > player2.tokenAmount){
      player1.tokenAmount += player2.tokenAmount
      player2.tokenAmount = 0
      kill(player2)
    }
    if(player1.tokenAmount < player2.tokenAmount){
      player2.tokenAmount += player1.tokenAmount
      player1.tokenAmount = 0
      kill(player1)
    }
    else{
      val killvalue = random.nextInt(100)
      if(killvalue <= 50){
        kill(player1)
      }
      if(killvalue > 50){
        kill(player2)
      }
    }
  }
}
