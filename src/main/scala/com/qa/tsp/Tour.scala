package com.qa.tsp

import scala.reflect.internal.util.Collections
import scala.util.Random
import com.qa.entities.Product

/**
 * @author pnewman
 */
class Tour(stops:List[Product]) {
  //TODO Rewrite
  val fitness=getFitness

  def getStop(tourPosition:Int):Product={
    stops.apply(tourPosition)
  }
  def setStop(tourPosition:Int, product:Product):List[Product]={
    stops.updated(tourPosition, product)
  }
  def getTourDistance:Int={
    def distanceCounter(count:Int, distance:Int):Int={
      def getThisDistance(endTour:Boolean):Int={
        val currentStop = getStop(count)
        val nextStop = endTour match{
          case false => getStop(count+1)
          case true => getStop(0)
        }
        currentStop.Product.getDistance(nextStop)
      }
      if(count<stops.length){
        val thisDistance = getThisDistance(false)
        distanceCounter(count.+(1), distance.+(thisDistance))
      }
      else{
        val thisDistance = getThisDistance(true)
        distance.+(thisDistance)
      }
    }    
    distanceCounter(0,0)
  }
  def generateIndividual{
    Random.shuffle(stops)
  }
  def getFitness:Double={
    1/getTourDistance.toDouble
  }
}