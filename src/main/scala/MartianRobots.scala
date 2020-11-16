import grid_location.PositionParser

import scala.io.StdIn.readLine

object MartianRobots {
  def main(args: Array[String]): Unit = {
    println("### Martian robots challenge ###")

    println("Enter grid upper right coordinates")
    val upperRightCoordinates = readLine()
    val grid = Grid(GridUpperBoundParser.parse(upperRightCoordinates.trim))

    while (true) {
      println("Enter robot position")
      val position = readLine()
      val gridPosition = PositionParser.parse(position.trim)
      val robot = Robot(grid, gridPosition)

      println("Enter directions")
      val directions = readLine()
      require(directions.length < 100, "No of directions should be less than 100")
      val finalPosition = robot.move(directions.trim)
      println(s"$finalPosition \n\n")
    }
  }
}