package grid_location

sealed trait Position

case class GridPosition(coordinates: Coordinates, orientation: String) extends Position {
  require(coordinates.x >= 0 && coordinates.x <= 50, "invalid x coordinate. must be >=0 and <=50")
  require(coordinates.y >= 0 && coordinates.y <= 50, "invalid y coordinate. must be >=0 and <=50")
  require(orientations.contains(orientation), s"invalid orientation '$orientation'. must be '${orientations.keys.mkString("|")}'")

  def change(direction: String) = {
    require(directions.contains(direction), s"invalid direction '$direction'. must be '${directions.keys.mkString("|")}'")

    val newOrientation = newOrientationMatrix(orientations(orientation))(directions(direction))

    val updatedCoordinate = direction match {
      case "F" => newCoordinateFnByOrientation(newOrientation)(coordinates)
      case _ => coordinates
    }

    GridPosition(updatedCoordinate, newOrientation)
  }

  override def toString = s"$coordinates $orientation"
}

case object LOST extends Position