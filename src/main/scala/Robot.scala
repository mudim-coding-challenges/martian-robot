import grid_location.{GridPosition, LOST, Position}

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

case class Robot(grid: Grid, position: GridPosition) {
  val history = ListBuffer.empty[Position]

  require(!grid.isOffGrid(position.coordinates), s"Given coordinates are out of bounds, top right at ${grid.topRight}")

  def move(directions: String): String = {
    @tailrec
    def changeDirection(position: GridPosition, directions: String): Unit = {
      history.append(position)

      if (directions.nonEmpty) {
        val direction = directions.head.toString

        if (grid.detectScent(position, direction)) history.append(LOST)
        else changeDirection(position.change(direction), directions.tail)
      }
    }

    changeDirection(position, directions)
    history.last match {
      case LOST => history.takeRight(2).map(_.toString).mkString(" ")
      case _ => history.last.toString
    }
  }
}
