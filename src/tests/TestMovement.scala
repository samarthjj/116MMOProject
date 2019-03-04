package tests

import Project.Game
import Project.objects.Player
import org.scalatest._

class TestMovement extends FunSuite{
  test("Movement Tested"){
    val game = new Game

    val Sam: Player = new Player("Sam", 1, 1)

    game.move("w", Sam)
    game.move("a", Sam)

    assert(Sam.xPosition == 1)
    assert(Sam.yPosition == 1)


    game.move("d", Sam)
    game.move("s", Sam)

    assert(Sam.xPosition == 2)
    assert(Sam.yPosition == 2)
  }
}
