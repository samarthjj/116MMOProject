package tests

import org.scalatest._
import Project.Game
import Project.objects._

class TestHitDetection extends FunSuite{

  //this tests the functionality of the hit detection method and only that
  //meaning fight and kill will not be tested here and will be tested in another testing class
  test("Hit Detection Tested"){
    val Sam: Player = new Player("Sam", 5, 5)
    val Dave: Player = new Player("Dave", 5, 5)
    val John: Player = new Player("John", 3, 9)

    assert(hitdetectionTest(Sam, Dave) == true, "testing hitdection on same tile-- true")
    assert(hitdetectionTest(Sam, John) == false, "testing hitdection on different tile-- false")
  }


  //the same hitdetection method in Game but returns a boolean if the players are on the same tile
  //instead of initiating the fight and kill chain
  def hitdetectionTest(player1: Player ,player2: Player): Boolean = {
    player1.xPosition == player2.xPosition && player1.yPosition == player2.yPosition
  }

}
