package tests

import Project.Game
import Project.objects.Player
import org.scalatest._

class TestMovement extends FunSuite{
  test("Movement Tested"){
    val game = new Game
    //Based on a game board of 39 tiles long in the x direction and 20 in the y
    val TopLeftCorner: Player = new Player("Sam", 1, 1)
    val BottomLeftCorner: Player = new Player("John", 1, 18)
    val TopRightCorner: Player = new Player("Uh", 37, 1)
    val BottomRightCorner: Player = new Player("UUUUUUhhhh", 37, 18)
    //Testing Top Left Corner
    game.move("w", TopLeftCorner)
    game.move("a", TopLeftCorner)
    assert(TopLeftCorner.xPosition == 1, "testing if you can move left when you are on the top left corner-not allowed")
    assert(TopLeftCorner.yPosition == 1, "testing if you can move up if you are on the top left corner-not allowed")
    game.move("d", TopLeftCorner)
    game.move("s", TopLeftCorner)
    assert(TopLeftCorner.xPosition == 2, "testing moving right when you are on the top left corner-allowed")
    assert(TopLeftCorner.yPosition == 2, "testing moving down when you are on the top left corner-allowed")

    //Testing Bottom Left Corner
    game.move("s", BottomLeftCorner)
    game.move("a", BottomLeftCorner)
    assert(BottomLeftCorner.xPosition == 1, "testing if you can move left when you are on the bottom left corner-not allowed")
    assert(BottomLeftCorner.yPosition == 18, "testing if you can move down if you are on the bottom left corner-not allowed")
    game.move("d", BottomLeftCorner)
    game.move("w", BottomLeftCorner)
    assert(BottomLeftCorner.xPosition == 2, "testing moving right when you are on the bottom left corner-allowed")
    assert(BottomLeftCorner.yPosition == 17, "testing moving up when you are on the bottom left corner-allowed")

    //Testing Top Right Corner
    game.move("w", TopRightCorner)
    game.move("d", TopRightCorner)
    assert(TopRightCorner.xPosition == 37, "testing if you can move right when you are on the top right corner-not allowed")
    assert(TopRightCorner.yPosition == 1, "testing if you can move up if you are on the top right corner-not allowed")
    game.move("a", TopRightCorner)
    game.move("s", TopRightCorner)
    assert(TopRightCorner.xPosition == 36, "testing moving left when you are on the top right corner-allowed")
    assert(TopRightCorner.yPosition == 2, "testing moving down when you are on the top right corner-allowed")

    //Testing Bottom Right Corner
    game.move("s", BottomLeftCorner)
    game.move("d", BottomLeftCorner)
    assert(BottomRightCorner.xPosition == 37, "testing if you can move right when you are on the bottom right corner-not allowed")
    assert(BottomRightCorner.yPosition == 18, "testing if you can move down if you are on the bottom right corner-not allowed")
    game.move("a", BottomRightCorner)
    game.move("w", BottomRightCorner)
    assert(BottomRightCorner.xPosition == 36, "testing moving left when you are on the bottom right corner-allowed")
    assert(BottomRightCorner.yPosition == 17, "testing moving up when you are on the bottom right corner-allowed")

    //Testing a player at position at point 2,3 that after multiple movements should end up at 3,3
    val randomPlayer: Player = new Player("randomName#2332", 2, 3)
    game.move("w", randomPlayer)//y at 2
    game.move("w", randomPlayer)//y at 1
    game.move("w", randomPlayer)//y at 1
    game.move("a", randomPlayer)// x at 1
    game.move("a", randomPlayer)// x at 1
    game.move("s", randomPlayer)//y at 2
    game.move("s", randomPlayer)// y at 3
    game.move("d", randomPlayer)// x at 2
    game.move("d", randomPlayer)// x at 3
    assert(randomPlayer.xPosition == 3, "testing x position for random player")
    assert(randomPlayer.yPosition == 3, "testing y position for random player")
  }
}
