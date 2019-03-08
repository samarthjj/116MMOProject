package tests

import org.scalatest._
import Project.Game
import Project.objects._

class TestFight extends FunSuite
{
  //this tests the functionality of the fight method and only that
  //meaning hit detection and kill will not be tested here and will be tested in another testing class
  test("testing fight")
  {
    val Sam: Player = new Player("Sam", 5, 5)
    Sam.tokenAmount = 5
    val Dave: Player = new Player("Dave", 5, 5)
    Dave.tokenAmount = 9
    val John: Player = new Player("John", 3, 9)
    John.tokenAmount = 5

    fightTest(Sam,Dave)
    assert(Dave.tokenAmount == 14 && Sam.tokenAmount == 0,
      "Testing fight for Dave and John -- Dave should win and have token of 14")
    Sam.tokenAmount = 5 //reset Sam's token to 5 since he lost
    fightTest(Sam,John)
    assert(((Sam.tokenAmount == 0 && John.tokenAmount == 10) || (Sam.tokenAmount == 10 && John.tokenAmount ==0)),
    " Testing the fight function when 2 players have the same amount of tokens-- one of the two should lose")
  }

  //This is the fight method in Game but does not contain the kill method
  def fightTest(player1: Player, player2: Player): Unit = {
    val random = scala.util.Random
    if(player1.tokenAmount > player2.tokenAmount){
      player1.tokenAmount += player2.tokenAmount
      player2.tokenAmount = 0
    }
    if(player1.tokenAmount < player2.tokenAmount){
      player2.tokenAmount += player1.tokenAmount
      player1.tokenAmount = 0
    }
    else{
      val killvalue = random.nextInt(100)
      //if value is less than 50 player one would be killed
      if(killvalue <= 50)
      {
        player2.tokenAmount += player1.tokenAmount
        player1.tokenAmount = 0
      }
      //if value is greater than 50 player two would be killed
      if(killvalue > 50)
      {
        player1.tokenAmount += player2.tokenAmount
        player2.tokenAmount = 0
      }
    }
  }
}
