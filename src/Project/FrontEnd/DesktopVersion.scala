package Project.FrontEnd

import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Rectangle, Shape}
import scalafx.scene.{Group, Scene}
import Project.Game

object DesktopVersion extends JFXApp {

  val game = new Game()
  game.spawnPlayer("player1")
  game.playerMap("player1").tokenAmount = 2
  game.spawnPlayer("player2")
  game.spawnPlayer("player3")
  game.spawnPlayer("adsf")
  game.spawnPlayer("asdlfkj")
  game.playerMap("player2").tokenAmount = 50

  val gridWidth: Double = 30
  val gridHeight: Double = 30

  val windowWidth: Double = gridWidth * 37
  val windowHeight: Double = gridHeight * 18

  val playerSpriteSize: Double = 20
  val lineThickness = 0.1

  var platformSprites: Map[Int, Shape] = Map[Int, Shape]()

  var sceneGraphics: Group = new Group {}
  var sceneGrid: Group = new Group {}

  griditize()
  generatePlayers()

  def keyPressed(keyCode: KeyCode, game: Game): Unit = {
    keyCode.getName match {
      case "W" => game.move("w", game.playerMap("player1"))
      case "A" => game.move("a", game.playerMap("player1"))
      case "S" => game.move("s", game.playerMap("player1"))
      case "D" => game.move("d", game.playerMap("player1"))
    }
  }

  def playerSprite(xLocation: Double, yLocation: Double, color: Color): Shape = {
    new Rectangle {
      width = playerSpriteSize
      height = playerSpriteSize
      translateX = (xLocation - 1) * gridWidth + 5
      translateY = (yLocation - 1) * gridHeight + 5
      fill = color
    }
  }

  def generatePlayers(): Unit = {
    sceneGraphics.getChildren.clear()
    for(player <- game.playerMap.values){
      val sprite = playerSprite(player.xPosition, player.yPosition, player.color)
      sceneGraphics.children.add(sprite)
    }
  }

  def verticalLine(xval: Int): Rectangle = {
    new Rectangle() {
      width = lineThickness
      height = windowHeight
      translateX = gridWidth * xval
      fill = Color.Black
    }
  }

  def horizontalLine(yval: Int): Rectangle = {
    new Rectangle() {
      width = windowWidth
      height = lineThickness
      translateY = gridHeight * yval
      fill = Color.Black
    }
  }

  def griditize(): Unit = {
    for(i <- 1 to 37){
      sceneGrid.children.add(verticalLine(i))
    }
    for(j <- 1 to 18){
      sceneGrid.children.add(horizontalLine(j))
    }
  }

  this.stage = new PrimaryStage {
    this.title = "Desktop App"
    scene = new Scene(windowWidth, windowHeight) {
      content = List(sceneGrid, sceneGraphics)
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode, game))
    }

    val update: Long => Unit = (time: Long) => {
      generatePlayers()
    }

    AnimationTimer(update).start()
  }

}
