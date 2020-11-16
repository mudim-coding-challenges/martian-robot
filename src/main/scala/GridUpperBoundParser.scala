import grid_location.Coordinates

import scala.util.control.Exception.allCatch

object GridUpperBoundParser {
  def parse(upperBounds: String): Coordinates = {
    require(upperBounds.nonEmpty, "invalid empty upper bounds")
    val coordinates = upperBounds.split(" ")
    require(coordinates.size == 2, "incorrect no of coordinates, required 2")

    val (maybeX, maybeY) = (allCatch.opt(coordinates(0).toInt), allCatch.opt(coordinates(1).toInt))

    require(maybeX.isDefined && maybeX.get >= 0 && maybeX.get <= 50, "invalid x coordinate. must be numeric, >=0 and <=50")
    require(maybeY.isDefined && maybeY.get >= 0 && maybeY.get <= 50, "invalid y coordinate. must be numeric, >=0 and <=50")

    Coordinates(maybeX.get, maybeY.get)
  }
}
