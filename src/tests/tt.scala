package tests

import Project.Game
import Project.objects._
import org.mongodb.scala._

  object testing{



    def main(args: Array[String]): Unit = {
      val random = scala.util.Random
      for (y <- 1 to 100)
        {
          val x: Int = 1 + random.nextInt((37 - 1) + 1)
          val y: Int = 1 + random.nextInt((18- 1) + 1)
          println(x, y)
        }

    }
  }

