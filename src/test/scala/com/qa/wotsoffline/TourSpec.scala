package com.qa.wotsoffline

import com.qa.wotsunit.UnitSpec
import com.qa.tsp.Tour
import com.qa.entities.Product

class TourSpec extends UnitSpec{
  
  def testSetStop{
    "The setStop method" should "update the value of the stop attribute at the given index" in{
      val stops = List(new Product,new Product, new Product)
      val tour = new Tour(stops)
      val index = 1
      val newProduct = new Product
      tour.setStop(index,newProduct).apply(index) should be (newProduct)
    }
  }
  
  def testGetTourDistance{
    "The getTourDistance method" should "return the total distance to travel between a list of ordered points and return to the start" in{
      val list1 = List(new Product('A',1), new Product('B',2), new Product('C',3))
      val list2 = List(new Product('A',1), new Product('C',3), new Product('B',2))
      val list3 = List(new Product('A',1), new Product('F',3), new Product('B',2))
      val tour1 = new Tour(list1)
      val tour2 = new Tour(list2)
      val tour3 = new Tour(list3)
      tour1.getTourDistance should be (8) //2+2+4
      tour2.getTourDistance should be (8) //4+2+2
      tour3.getTourDistance should be (14) //7+5+2
    }
  }
  
  def testGenerateIndividual{
    "The generateIndividual method" should "return a list containing the same elements as the given parameter" in {
      val list = List(new Product('A',1), new Product('C',3), new Product('B',2))
      val tour = new Tour(list)
      tour.stops.foreach{stop =>
        tour.generateIndividual.contains(stop) should be (true)
      }
    }
  }
  
  testSetStop
  testGetTourDistance
  testGenerateIndividual
}