package tests

import Project.Game
import Project.objects._
import org.mongodb.scala._

  object testing{

    var playerMap: Map[String, Player] = Map()
    /*
      update spawnPLayer with placing a player into a larger map of players
      ss
     */
    def spawnPlayer(name: String): Unit =
    {
      val random = scala.util.Random
      val x: Int = 1 + random.nextInt((99 - 1) + 1)
      val y: Int = 1 + random.nextInt((99 - 1) + 1)
      // this generates a random number between 1 and 99 (both inclusive)
      val player: Player = new Player(name,x ,y)
      playerMap = playerMap + (name -> player)
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

    def kill(name: String): Map[String, Player] = {
      if (playerMap.keySet.contains(name)) {
        playerMap -= name
      }
      playerMap
    }

    def main(args: Array[String]): Unit = {
      val Sam: Player = new Player("Sam", 5, 5)
      Sam.tokenAmount = 9
      val Dave: Player = new Player("Dave", 5, 5)
      Dave.tokenAmount = 8
      playerMap += ("Dave" -> Dave)
      playerMap += ("Sam" -> Sam)
      println(playerMap)
      println(hitdetection(playerMap("Sam"), playerMap("Dave")))
      println(playerMap)
    }
  }

