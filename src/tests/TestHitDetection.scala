package tests

import org.scalatest._
import Project.Game
import Project.objects._

class TestHitDetection extends FunSuite{

  test("Hit Detection Tested"){
    val game = new Game

    val Sam: Player = new Player("Sam", 5, 5)
    Sam.tokenAmount = 9
    val Dave: Player = new Player("Dave", 5, 5)
    Dave.tokenAmount = 8

    game.playerMap += ("Sam" -> Sam)
    game.playerMap += ("Dave" -> Dave)
    game.hitdetection(Sam, Dave)

    assert(game.playerMap.size == 1)
    assert(game.playerMap.keySet.contains("Sam"))
    assert(Sam.tokenAmount == 17)
  }



}
