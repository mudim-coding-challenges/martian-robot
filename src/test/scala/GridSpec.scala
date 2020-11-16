import grid_location.{Coordinates, GridPosition}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class GridSpec extends AnyFunSpec with Matchers {
  describe("initialised grid ") {
    describe("when checked for off grid") {
      val grid = Grid(Coordinates(5,5))

      it("should return true if new coordinates out of top right") {
        grid.detectScent(GridPosition(Coordinates(5,5), "N"), "F") shouldBe true
      }

      it("should return true if new coordinates out of bottom left") {
        grid.detectScent(GridPosition(Coordinates(0,0), "S"), "F") shouldBe true
      }

      it("should return false if new coordinates at bottom left") {
        grid.detectScent(GridPosition(Coordinates(0,1), "S"), "F") shouldBe false
      }

      it("should return false if new coordinates at top right") {
        grid.detectScent(GridPosition(Coordinates(5,4), "N"), "F") shouldBe false
      }
    }
  }
}
