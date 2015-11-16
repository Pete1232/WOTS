package com.qa.wotsoffline

import com.qa.wotsunit.UnitSpec
import com.qa.tsp.Population
import com.qa.tsp.Tour

/**
 * @author pnewman
 */
class PopultationSpec extends UnitSpec{
  
  def testGenerateEmptyPop{
    "The generateEmptyPop method" should "return an array of null tours" in{
      val pop = Population.generateEmptyPop(10)
      def checkEntries(population:Array[Tour]){
        if(population.tail.length !== 0){
          population.head should be (null)
          checkEntries(population.tail)
        }
      }
      checkEntries(pop)
    }
  }
  testGenerateEmptyPop  
}