import grid_location.{Coordinates, GridPosition}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class RobotSpec extends AnyFunSpec with Matchers {
  val grid = Grid(Coordinates(5,3))

  describe("robot at a grid position") {
    describe("when moved in valid directions and is not off grid") {
      it("should return last position") {
        val initialGridPosition = GridPosition(Coordinates(1, 1), "E")
        val finalPosition = Robot(grid, initialGridPosition).move("RFRFRFRF")

        finalPosition shouldBe "1 1 E"
      }
    }

    describe("when moved in valid directions and is off grid") {
      it("should return previous known position and lost") {
        val initialGridPosition = GridPosition(Coordinates(3, 2), "N")
        val finalPosition = Robot(grid, initialGridPosition).move("FRRFLLFFRRFLL")

        finalPosition shouldBe "3 3 N LOST"
      }
    }
  }

  describe("robot") {
    describe("when initialised at invalid grid position") {
      it("should fail initialisation") {
        val thrown = the [IllegalArgumentException] thrownBy Robot(grid, GridPosition(Coordinates(5,4), "N"))
        thrown.getMessage shouldBe "requirement failed: Given coordinates are out of bounds, top right at 5 3"
      }
    }
  }
}
