package tests

import Project.objects.Player
import Project.Game
import org.scalatest.FunSuite

class TestSpawn extends FunSuite
{
  test("testing spawning")
  {
    val game = new Game
    var randomNameList: List[String] = List()
    val random = new scala.util.Random()
    for (i <- 0 to 10000)
      {
        val randomString: String = random.alphanumeric.take(10).mkString
        randomNameList = randomNameList :+ randomString
      }
    /*
    this for loop creates a List of 10000 random Strings of length 10 that includes letters and numbers
    the possible generated string could be the alphabet both lower case and upper case and the digits 0-9
    this totals to 62 possible values
    but it does not guarantee that each String is unique
     */
    for (x <- randomNameList)
      {
        game.spawnPlayer(x)
        assert(game.playerMap.keySet.contains(x), "tests if the player map contains the player with name")
        val xcord = game.playerMap(x).xPosition
        val ycord = game.playerMap(x).yPosition
        assert(checksOnWall(xcord, ycord), "testing if player spawns on the boundary")
      }
  }

  def checksOnWall(xcord: Int, ycord: Int): Boolean =
  {
    xcord != 0 && xcord != 38 && ycord != 0 && ycord != 19
  }

}
