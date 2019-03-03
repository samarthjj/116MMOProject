package Project

import
import objects.Player

class Game
{
  /*
    since the map is bordered by walls
   */
  def spawnPlayer(): Unit =
  {
    val random = scala.util.Random
    val x: Int = 1 + random.nextInt((99 - 1) + 1)
    val y: Int = 1 + random.nextInt((99 - 1) + 1)
      // this generates a random number between 1 and 99 (both inclusive)
    //sasfafasf

    val player: Player = new Player("name",x ,y)
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
