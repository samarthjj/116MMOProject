package tests

  object testing
  {
    def main(args: Array[String]): Unit =
    {
      val random = scala.util.Random
      for (value <- 1 to 100)
        {
          val x: Int = 1 + random.nextInt((99 - 1) + 1)
          println(x)
        }
    }
  }

