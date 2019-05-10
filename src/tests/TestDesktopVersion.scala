package tests

import Project.FrontEnd.DesktopVersion.{playerSprite}
import io.socket.client.{IO, Socket}
import org.scalatest._
import play.api.libs.json.{JsValue, Json}
import scalafx.scene.Group
import scalafx.scene.paint.Color
import scalafx.scene.shape.Shape
import scalafx.scene.text.{Font, Text}

class TestDesktopVersion extends FunSuite{

  test("generate players") {

    val JsonGameState = "{\"d2807283f2de4192a7d54e06d2ef1d59\":{\"x\":14,\"y\":1,\"tokens\":1}," + "\"1daa07c5180642e1a81077ac6f37ad8e\":{\"x\":1,\"y\":8,\"tokens\":1}}"

    val gameState: JsValue = Json.parse(JsonGameState)
    val mapped: Map[String, Map[String, Int]] = gameState.as[Map[String, Map[String, Int]]]

    generatePlayers(mapped)

    assert(sceneGraphics.children.size == 4)
  }

  var socket: Socket = IO.socket("http://localhost:8080/")

  val gridWidth: Double = 30
  val gridHeight: Double = 30

  val windowWidth: Double = gridWidth * 39
  val windowHeight: Double = gridHeight * 20

  val playerSpriteSize: Double = 20
  val lineThickness = 0.1

  var platformSprites: Map[Int, Shape] = Map[Int, Shape]()

  var sceneGraphics: Group = new Group {}
  var sceneGrid: Group = new Group {}

  def playerScore1(xLocation: Int, yLocation: Int, score: Int): Text ={
    new Text {
      text = score.toString
      font = Font.font("Arial", 14)
      translateX = xLocation * gridWidth + 11
      translateY = yLocation * gridHeight + 20
      fill = Color.Black
    }
  }

  def playerScore10(xLocation: Int, yLocation: Int, score: Int): Text ={
    new Text {
      text = score.toString
      font = Font.font("Arial", 14)
      translateX = xLocation * gridWidth + 7
      translateY = yLocation * gridHeight + 20
      fill = Color.Black
    }
  }

  def generatePlayers(gameState: Map[String, Map[String, Int]]): Unit = {
    sceneGraphics.getChildren.clear()
    var sprite: Shape = null
    for((player, value) <- gameState){
      if(socket.id() == player){
        sprite = playerSprite(value("x"), value("y"), Color.web("#FF4500"))
      }else{
        sprite = playerSprite(value("x"), value("y"), Color.web("#0080A0"))
      }
      sceneGraphics.children.add(sprite)
      if(value("tokens") < 10) {
        sceneGraphics.children.add(playerScore1(value("x"), value("y"), value("tokens")))
      }else{
        sceneGraphics.children.add(playerScore10(value("x"), value("y"), value("tokens")))
      }
    }
  }


}
