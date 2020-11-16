import grid_location.GridPosition

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

case class Robot(position: GridPosition) {
  private val history = ListBuffer[GridPosition](position)

  def move(directions: String): GridPosition = {
    @tailrec
    def changeDirection(position: GridPosition, directions: String): Unit = {
      if (directions.nonEmpty) {
        val newPosition = position.change(directions.head.toString)
        history.append(newPosition)
        changeDirection(newPosition, directions.tail)
      }
    }

    changeDirection(position, directions)
    history.last
  }
}
