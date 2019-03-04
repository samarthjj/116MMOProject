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
    /*
    this for loop creates a List of 10000 random Strings of length 10 that includes letters and numbers
    but it is not guarantee that each String is unique
     */
    for (i <- 0 to 10000)
      {
        val randomString: String = random.alphanumeric.take(10).mkString
        randomNameList = randomNameList :+ randomString
      }

    //This for loop checks that the player created is in the larger dictionary
    for (x <- randomNameList)
      {
        game.spawnPlayer(x)
        assert(game.playerMap.keySet.contains(x), "tests if the player map contains the player with name")
      }
    //this checks that each player in the dictionary didn't spawn on a wall
    for (y <- game.playerMap.values)
      {
        val xcord = y.xPosition
        val ycord = y.yPosition
        assert(checksOnWall(xcord, ycord), "testing if player spawns on the boundary")
      }
  }

  def checksOnWall(xcord: Int, ycord: Int): Boolean =
  {
    xcord != 0 && xcord != 38 && ycord != 0 && ycord != 19
  }

}
