package Project.objects

import scalafx.scene.paint.Color

class Player (var name: String,  var xPosition: Int, var yPosition: Int)
{
  var tokenAmount = 1
  val color: Color = Color.color(Math.random(), Math.random(), Math.random())
}
