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

    assert(sceneGraphics.children.size == 2)
  }

  var socket: Socket = IO.socket("http://localhost:8080/")
  var sceneGraphics: Group = new Group {}



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
    }
  }


}
