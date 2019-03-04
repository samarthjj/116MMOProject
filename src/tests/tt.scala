package tests

import Project.Game
import Project.objects._
import org.mongodb.scala._

  object testing{



    def main(args: Array[String]): Unit = {
      var randomNameList: List[String] = List()
      val random = new scala.util.Random()
      for (i <- 1 to 10000) {
        val x: Int = 1 + random.nextInt((37 - 1) + 1)
        val y: Int = 1 + random.nextInt((18 - 1) + 1)
        println(x, y)
      }

    }
  }

