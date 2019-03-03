package tests

import Project.objects._

  object testing{
    var playerMap: Map[String, Map[String, Int]] = Map()
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
        val playerEntry: Map[String, Int] = Map("x" -> player.xPosition, "y" -> player.yPosition, "token" -> player.tokenAmount)
        playerMap = playerMap + (name -> playerEntry)
      }
    def kill(name: String): Map[String, Map[String, Int]] = {
      if (playerMap.keySet.contains(name)) {
        playerMap -= name
      }
      playerMap
    }


    def main(args: Array[String]): Unit = {

      spawnPlayer("Sam")
      spawnPlayer("Dave")
      print(kill("Sam"))
    }
  }

