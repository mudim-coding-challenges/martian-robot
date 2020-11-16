import grid_location.{Coordinates, GridPosition}

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

case class Grid(topRight: Coordinates) {
  val lowerLeft: Coordinates = Coordinates(0, 0)
  val scents = ListBuffer.empty[(GridPosition, String)]

  def detectScent(position: GridPosition, direction: String) = {
    val offGrid = Try(position.change(direction).coordinates) match {
      case Success(coordinates) => isOffGrid(coordinates)
      case Failure(_) => true
    }

    if (offGrid) scents.append((position, direction))

    offGrid
  }

  def isOffGrid(coordinates: Coordinates) = {
    coordinates.x < lowerLeft.x || coordinates.x > topRight.x ||
      coordinates.y < lowerLeft.y || coordinates.y > topRight.y
  }

  def isScent(position: GridPosition, direction: String) = scents.contains((position, direction))
}
