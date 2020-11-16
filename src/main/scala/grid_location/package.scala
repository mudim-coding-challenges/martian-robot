import grid_location.Coordinates

package object grid_location {
  val directions = Map("L" -> 0, "R" -> 1, "F" -> 2)
  val orientations = Map("N" -> 0, "E" -> 1, "S" -> 2, "W" -> 3)
  val newCoordinateFnByOrientation = Map[String, Coordinates => Coordinates](
    "N" -> (_.moveBy(0, 1)),
    "E" -> (_.moveBy(1, 0)),
    "S" -> (_.moveBy(0, -1)),
    "W" -> (_.moveBy(-1, 0))
  )

  /**
   *   | L R F
   * --|---------
   * N | W E N
   * E | N S E
   * S | E W S
   * W | S N W
   */
  val newOrientationMatrix = {
    val matrix = Array.ofDim[String](orientations.size, directions.size)
    matrix(0)(0) = "W"
    matrix(0)(1) = "E"
    matrix(0)(2) = "N"
    matrix(1)(0) = "N"
    matrix(1)(1) = "S"
    matrix(1)(2) = "E"
    matrix(2)(0) = "E"
    matrix(2)(1) = "W"
    matrix(2)(2) = "S"
    matrix(3)(0) = "S"
    matrix(3)(1) = "N"
    matrix(3)(2) = "W"
    matrix
  }
}
