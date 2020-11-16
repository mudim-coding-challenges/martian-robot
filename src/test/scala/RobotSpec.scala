import grid_location.{Coordinates, GridPosition}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class RobotSpec extends AnyFunSpec with Matchers {
  describe("robot at a grid position") {
    describe("when moved in valid directions") {
      it("should return last position") {
        val initialGridPosition = GridPosition(Coordinates(1, 1), "E")
        val finalPosition = Robot(initialGridPosition).move("RFRFRFRF")

        finalPosition shouldBe GridPosition(Coordinates(1, 1), "E")
      }
    }
  }
}
