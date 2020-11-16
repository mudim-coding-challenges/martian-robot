import grid_location.Coordinates

case class Grid(topRight: Coordinates) {
  val lowerLeft: Coordinates = Coordinates(0, 0)

  def isOffGrid(coordinates: Coordinates) = {
    coordinates.x < lowerLeft.x || coordinates.x > topRight.x ||
      coordinates.y < lowerLeft.y || coordinates.y > topRight.y
  }
}
