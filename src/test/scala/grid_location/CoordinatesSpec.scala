package grid_location

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class CoordinatesSpec extends AnyFunSpec with Matchers {
  describe("coordinate") {
    describe("when moved by positive increments") {
      it("should be a valid coordinate") {
        Coordinates(1, 1).moveBy(0, 1) shouldBe Coordinates(1, 2)
      }
    }

    describe("when moved by negative increments") {
      it("should be a valid coordinate") {
        Coordinates(1, 1).moveBy(0, -1) shouldBe Coordinates(1, 0)
      }
    }

    describe("when moved by same increments for coordinates") {
      it("should fail to create new coordinate") {
        val thrown = the [IllegalArgumentException] thrownBy Coordinates(1,1).moveBy(1,1)
        thrown.getMessage shouldBe "requirement failed: invalid move: move coordinates cannot be same"
      }
    }

    describe("when moved by out of bounds increments for coordinates") {
      it("should fail to create new coordinate") {
        val thrown = the [IllegalArgumentException] thrownBy Coordinates(1,1).moveBy(2,1)
        thrown.getMessage shouldBe "requirement failed: invalid increment for x coordinate, valid values (-1, 0, 1)"
      }
    }
  }
}
