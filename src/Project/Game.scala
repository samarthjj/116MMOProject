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
}
