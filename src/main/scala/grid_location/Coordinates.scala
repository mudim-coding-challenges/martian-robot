package grid_location

case class Coordinates(x: Int, y: Int) {
  def moveBy(xIncr: Int, yIncr: Int) = {
    require(xIncr != yIncr, "invalid move: move coordinates cannot be same")
    require(xIncr >= -1 && xIncr <= 1, "invalid increment for x coordinate, valid values (-1, 0, 1)")
    require(yIncr >= -1 && yIncr <= 1, "invalid increment for y coordinate, valid values (-1, 0, 1)")

    Coordinates(x + xIncr, y + yIncr)
  }

  override def toString = s"$x $y"
}
