package tests

import org.scalatest._
import Project._
import Project.objects.Player

class TestHitDetectionRandomKill extends FunSuite{

    test("Random Kill Tested"){
      val game = new Game

      val Sam: Player = new Player("Sam", 5, 5)
      Sam.tokenAmount = 8
      val Dave: Player = new Player("Dave", 5, 5)
      Dave.tokenAmount = 8

      game.playerMap += ("Sam" -> Sam)
      game.playerMap += ("Dave" -> Dave)
      game.hitdetection(Sam, Dave)

      assert(game.playerMap.size == 1)
    }


}
