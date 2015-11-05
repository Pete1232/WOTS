package com.qa.wotsunit

import com.qa.dummydata.DummyBuilder
import scala.util.Random

/**
 * @author pnewman
 */
class DummyBuilderSpec extends UnitSpec{
  val entries = Math.abs(Random.nextInt(200))
  "Calling addToArray" should "populate the given index of databaseArray with an entity of the given type" in {
  }
  "Calling buildEntityArray" should "populate ALL entries of databaseArray with entities of the given type" //TODO Implement test
}