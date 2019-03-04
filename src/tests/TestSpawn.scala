package tests

import Project.objects.Player
import Project.Game
import org.scalatest.FunSuite

class TestSpawn extends FunSuite
{
  test("testing spawning")
  {
    val game = new Game
    game.spawnPlayer("Sam")
    assert(game.playerMap.keySet.contains("Sam"), "tests if the map has Sam")
    val samMap = game.playerMap("Sam")
    val xcord = samMap.xPosition
    val ycord = samMap.yPosition
    assert(checksOnWall(xcord, ycord), "testing if player spawns on the boundary")

  }

  def checksOnWall(xcord: Int, ycord: Int): Boolean =
  {
    xcord != 0 && xcord != 37 && ycord != 0 && ycord != 18
  }

}
