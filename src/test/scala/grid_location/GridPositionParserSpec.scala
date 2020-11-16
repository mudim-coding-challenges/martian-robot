package grid_location

import grid_location.PositionParser
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class GridPositionParserSpec extends AnyFunSpec with Matchers {
  describe("valid position string") {
    describe("when parsed") {
      it("should create position with correct coordinates and orientation") {
        val position = PositionParser.parse("3 2 N")
        position.coordinate shouldBe Coordinates(3, 2)
        position.orientation shouldBe "N"
      }
    }
  }

  describe("invalid position string") {
    describe("with incorrect input length, when parsed") {
      it("should fail for length check") {
        val thrown = the [IllegalArgumentException] thrownBy PositionParser.parse("32")
        thrown.getMessage shouldBe "requirement failed: Incorrect position string. required format 'x y orientation'"
      }
    }

    describe("with incorrect orientation, when parsed") {
      it("should fail orientation check") {
        val thrown = the [IllegalArgumentException] thrownBy PositionParser.parse("3 2 P")
        thrown.getMessage shouldBe "requirement failed: invalid orientation 'P'. must be 'N|E|S|W'"
      }
    }

    describe("with non-numeric coordinate values, when parsed") {
      it("should fail coordinate check") {
        val thrown = the [IllegalArgumentException] thrownBy PositionParser.parse("a 2 N")
        thrown.getMessage shouldBe "requirement failed: invalid x coordinate. must be numeric"
      }
    }

    describe("with incorrect coordinate values, when parsed") {
      it("should fail coordinate check") {
        var thrown = the [IllegalArgumentException] thrownBy PositionParser.parse("300 2 N")
        thrown.getMessage shouldBe "requirement failed: invalid x coordinate. must be >=0 and <=50"

        thrown = the [IllegalArgumentException] thrownBy PositionParser.parse("-1 2 N")
        thrown.getMessage shouldBe "requirement failed: invalid x coordinate. must be >=0 and <=50"
      }
    }
  }
}
