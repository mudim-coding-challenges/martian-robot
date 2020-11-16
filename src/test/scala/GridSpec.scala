import grid_location.Coordinates
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class GridSpec extends AnyFunSpec with Matchers {
  describe("initialised grid ") {
    describe("when checked for off grid") {
      it("should return true for coordinates out of top right") {
        val grid = Grid(Coordinates(5,5))
        grid.isOffGrid(Coordinates(5,6)) shouldBe true
      }

      it("should return true for coordinates out of bottom left") {
        val grid = Grid(Coordinates(5,5))
        grid.isOffGrid(Coordinates(5,-1)) shouldBe true
      }

      it("should return false for coordinates at bottom left") {
        val grid = Grid(Coordinates(5,5))
        grid.isOffGrid(Coordinates(0,0)) shouldBe false
      }

      it("should return false for coordinates at top right") {
        val grid = Grid(Coordinates(5,5))
        grid.isOffGrid(Coordinates(5,5)) shouldBe false
      }

      it("should return false for coordinates within bounds") {
        val grid = Grid(Coordinates(5,5))
        grid.isOffGrid(Coordinates(5,1)) shouldBe false
      }
    }
  }
}
