package Project

import objects.Player
import org.mongodb.scala._

class Game
{
  /*
  ALL OF THE FUNCTIONS HERE ESPECIALLY spawnPlayer AND move IS BASED ON THE 39 TILES(X) BY 20 TILES(Y) MAP
  FOR DEMO 1
  THESE DIMENSIONS ARE NOT FINAL AND WILL MOST LIKELY CHANGE
   */

  var playerMap: Map[String, Player] = Map()

  //SpawnPlayer assumes the given String is already not included in the larger Map of players
  //It's Goal is to create a player with x, y coordinates that is not along the border
  def spawnPlayer(name: String): Unit =
  {
    val random = scala.util.Random
    val x: Int = 1 + random.nextInt((37 - 1) + 1)
    val y: Int = 1 + random.nextInt((18 - 1) + 1)
      // this generates a random number between 1 and 37 for x (both inclusive)
      // and a random number between 1 and 18 for y
    val player: Player = new Player(name,x ,y)
    playerMap = playerMap + (name -> player)
  }

  def move(input: String, player: Player): Unit =
  {
    val inputLower = input.toLowerCase()
    if(inputLower == "w")
      {
        if((player.yPosition - 1) != 0)
          {
            player.yPosition -= 1
          }
      }
    else if(inputLower == "a")
      {
        if((player.xPosition - 1) != 0)
        {
          player.xPosition -= 1
        }
      }
    else if(inputLower == "s")
      {
        if((player.yPosition + 1) != 19)
        {
          player.yPosition += 1
        }
      }
    else if(inputLower == "d")
      {
        if((player.xPosition + 1) != 38)
        {
          player.xPosition += 1
        }
      }
  }

  def hitdetection(player1: Player ,player2: Player): Unit = {
    if(player1.xPosition == player2.xPosition && player1.yPosition == player2.yPosition){
      fight(player1, player2)
    }
  }

  def kill(name: String): Map[String, Player] = {
    if (playerMap.keySet.contains(name)) {
      playerMap -= name
    }
    playerMap
  }

  def fight(player1: Player, player2: Player): Unit = {
    val random = scala.util.Random
    if(player1.tokenAmount > player2.tokenAmount){
      player1.tokenAmount += player2.tokenAmount
      player2.tokenAmount = 0
      kill(player2.name)
    }
    if(player1.tokenAmount < player2.tokenAmount){
      player2.tokenAmount += player1.tokenAmount
      player1.tokenAmount = 0
      kill(player1.name)
    }
    else{
      val killvalue = random.nextInt(100)
      if(killvalue <= 50){
        kill(player1.name)
      }
      if(killvalue > 50){
        kill(player2.name)
      }
    }
  }
}
