package grid

case class Position(coordinate: Coordinate, orientation: String) {
  require(coordinate.x >=0 && coordinate.x <= 50, "invalid x coordinate. must be >=0 and <=50")
  require(coordinate.y >=0 && coordinate.y <= 50, "invalid y coordinate. must be >=0 and <=50")
  require(orientations.contains(orientation), s"invalid orientation '$orientation'. must be '${orientations.keys.mkString("|")}'")

  def change(direction: String) = {
    require(directions.contains(direction), s"invalid direction '$direction'. must be '${directions.keys.mkString("|")}'")

    val newOrientation = newOrientationMatrix(orientations(orientation))(directions(direction))
    Position(newCoordinateFnByOrientation(newOrientation)(coordinate), newOrientation)
  }
}