package com.qa.wotsoffline

import com.qa.wotsunit.UnitSpec
import com.qa.tsp.Population
import com.qa.tsp.Tour
import com.qa.entities.Product

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
  
  def testSaveTour{
    "The saveTour method" should "update the population attribute by replacing the tour at the given index" in{
      val products = List(new Product, new Product, new Product)
      val tour = new Tour(products)
      val Population = new Population(5,products)
      Population.saveTour(1, tour)
      Population.population.apply(1) should be (tour)
    }
  }
  
  testGenerateEmptyPop  
  testSaveTour
}