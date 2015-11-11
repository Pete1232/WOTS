package com.qa.tsp;

import com.qa.entities.Product

class Test {

    def main(args:Array[String]) {

      val tour = new Tour(10)
      // Create and add our cities
      val product = new Product('A',1)
      tour.stops.updated(0, product)
      val product2 = new Product('B',1)
      tour.stops.updated(0, product2)
      val product3 = new Product('C',1)
      tour.stops.updated(0, product3)
      val product4 = new Product('D',1)
      tour.stops.updated(0, product4)
      val product5 = new Product('E',1)
      tour.stops.updated(0, product5)
      val product6 = new Product('F',1)
      tour.stops.updated(0, product6)
      val product7 = new Product('G',1)
      tour.stops.updated(0, product7)
      val product8 = new Product('H',1)
      tour.stops.updated(0, product8)
      val product9 = new Product('I',1)
      tour.stops.updated(0, product9)
      val product10 = new Product('J',1)
      tour.stops.updated(0, product10)

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