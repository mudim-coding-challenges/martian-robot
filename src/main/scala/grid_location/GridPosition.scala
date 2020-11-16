package grid_location

sealed trait Position

case class GridPosition(coordinate: Coordinates, orientation: String) extends Position {
  require(coordinate.x >= 0 && coordinate.x <= 50, "invalid x coordinate. must be >=0 and <=50")
  require(coordinate.y >= 0 && coordinate.y <= 50, "invalid y coordinate. must be >=0 and <=50")
  require(orientations.contains(orientation), s"invalid orientation '$orientation'. must be '${orientations.keys.mkString("|")}'")

  def change(direction: String) = {
    require(directions.contains(direction), s"invalid direction '$direction'. must be '${directions.keys.mkString("|")}'")

    val newOrientation = newOrientationMatrix(orientations(orientation))(directions(direction))

    val updatedCoordinate = direction match {
      case "F" => newCoordinateFnByOrientation(newOrientation)(coordinate)
      case _ => coordinate
    }

    GridPosition(updatedCoordinate, newOrientation)
  }

  override def toString = s"$coordinate $orientation"
}

case object Lost extends Position