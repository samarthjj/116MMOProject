package tests

import org.scalatest._
import Project.Game
import Project.Player

class TestKill extends FunSuite {
  //this test will only test the function kill
  //meaning hit detection and fight will not be tested here and will be tested in a different class
  test("testing kill") {

    val game = new Game
    val Sam: Player = new Player("Sam", 5, 5)
    val Dave: Player = new Player("Dave", 5, 5)
    val John: Player = new Player("John", 3, 9)
    game.playerMap += ("Sam" -> Sam)
    game.playerMap += ("Dave" -> Dave)
    game.playerMap += ("John" -> John)

    def kill(name: String): Unit = {
      if (game.playerMap.keySet.contains(name))
      {
        game.playerMap -= name
      }
    }

    kill("Sam")
    assert(game.playerMap.size == 2 && game.playerMap.contains("Dave") && game.playerMap.contains("John"),
      "Testing killing Sam -- after killing off Sam the map should have only 2 players Dave and John")
    kill("RandomName")
    assert(game.playerMap.size == 2 && game.playerMap.contains("Dave") && game.playerMap.contains("John"),
    "Testing killing a player with a name that is not in the map -- size 2 players Dave and John")
  }



}
