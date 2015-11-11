package com.qa.tsp;

import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

object Test {
  val logger = Logger(LoggerFactory.getLogger("Test.object"))
  def main(args:Array[String]) {
    val tour0 = new Tour(10)
    logger.debug("Initial empty tour: "+tour0.stops)
    // Create and add our cities
    val product = new Product('A',1)
    val tour2 = tour0.stops.updated(0, product)
    
    val product2 = new Product('B',1)
    val tour3 = tour2.updated(1, product2)
    
    val product3 = new Product('C',1)
    val tour4 = tour3.updated(2, product3)
    
    val product4 = new Product('D',1)
    val tour5 = tour4.updated(3, product4)
    
    val product5 = new Product('E',1)
    val tour6 = tour5.updated(4, product5)
    
    val product6 = new Product('F',1)
    val tour7 = tour6.updated(5, product6)
    
    val product7 = new Product('G',1)
    val tour8 = tour7.updated(6, product7)
    
    val product8 = new Product('H',1)
    val tour9 = tour8.updated(7, product8)
    
    val product9 = new Product('I',1)
    val tour10 = tour9.updated(8, product9)
    
    val product10 = new Product('J',1)
    val tour11 = tour10.updated(9, product10)
    
    val tour = new Tour(tour11)
    
    logger.debug("Populated tour: "+tour.stops)
    
    // Initialize population
    val pop = new Population(5,tour.stops)
    System.out.println("Initial distance: " + pop.getFittest.getTourDistance);

    // Evolve population for 100 generations
    def evolver(count:Int,pop:Population):Population={
      if(count<100)
        Algorithm.evolvePop(pop)
      pop
    }
    val newPop = evolver(0,pop)

    // Print final results
    println("Finished")
    println("Final distance: " + newPop.getFittest.getTourDistance)
    println("Solution:")
    println(pop.getFittest)
  }
}