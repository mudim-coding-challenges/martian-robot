package grid_location

import scala.util.control.Exception.allCatch

object PositionParser {
  def parse(positionStr: String): GridPosition = {
    val positionEntries = positionStr.split(" ")
    require(positionEntries.length == 3, "Incorrect position string. required format 'x y orientation'")

    val (maybeX, maybeY, orientation) = (allCatch.opt(positionEntries(0).toInt), allCatch.opt(positionEntries(1).toInt), positionEntries(2))

    require(maybeX.isDefined, "invalid x coordinate. must be numeric")
    require(maybeY.isDefined, "invalid y coordinate. must be numeric")

    val coordinate = Coordinates(maybeX.get, maybeY.get)

    GridPosition(coordinate, orientation)
  }
}
