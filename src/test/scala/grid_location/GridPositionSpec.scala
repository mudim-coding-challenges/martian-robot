package grid_location

import grid_location.Position
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class GridPositionSpec extends AnyFunSpec with Matchers {
  describe("valid position") {
    describe("when moved in valid direction") {
      it("should return a new position with correct coordinates and new orientation") {
        val currentCoordinate = Coordinates(1, 1)

        var newPosition = GridPosition(currentCoordinate, "N").change("L").change("F")
        newPosition.orientation shouldBe "W"
        newPosition.coordinate shouldBe Coordinates(0, 1)

        newPosition = GridPosition(currentCoordinate, "N").change("R").change("F")
        newPosition.orientation shouldBe "E"
        newPosition.coordinate shouldBe Coordinates(2, 1)

        // Safe to validate explicitly than doing generic as below. Risk of missing incorrect mappings

        /*val matrixEntries = for {
          o <- orientations.keys
        } yield for {
          d <- directions.keys
        } yield (o,d)

        matrixEntries.head.foreach {
          case (orientation, direction) =>
            val newPosition = Position(currentCoordinate, orientation).change(direction)

            val orientationIndex = orientations(orientation)
            val directionIndex = directions(direction)

            newPosition.orientation shouldBe newOrientationMatrix(orientationIndex)(directionIndex)
            newPosition.coordinate shouldBe newCoordinateFnByOrientation(newPosition.orientation)(currentCoordinate)
        }*/
      }
    }

    describe("when moved in invalid direction") {
      it("should fail direction check") {
        val currentCoordinate = Coordinates(1, 1)

        val thrown = the [IllegalArgumentException] thrownBy GridPosition(currentCoordinate, "N").change("X")
        thrown.getMessage shouldBe "requirement failed: invalid direction 'X'. must be 'L|R|F'"
      }
    }
  }

  describe("position with invalid orientation") {
    it("should fail orientation check") {
      val thrown = the [IllegalArgumentException] thrownBy GridPosition(Coordinates(1,1), "P").change("L")
      thrown.getMessage shouldBe "requirement failed: invalid orientation 'P'. must be 'N|E|S|W'"
    }
  }

  describe("position with incorrect coordinate values") {
    it("should fail coordinate check") {
      var thrown = the [IllegalArgumentException] thrownBy GridPosition(Coordinates(100,1), "N").change("L")
      thrown.getMessage shouldBe "requirement failed: invalid x coordinate. must be >=0 and <=50"

      thrown = the [IllegalArgumentException] thrownBy GridPosition(Coordinates(-1,1), "N").change("L")
      thrown.getMessage shouldBe "requirement failed: invalid x coordinate. must be >=0 and <=50"
    }
  }
}
