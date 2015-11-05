package com.qa.wotsunit

import org.scalatest._
import com.qa.repositoryimplementations.GenericRepositoryDummy
import com.qa.entities.Product

class GenericRepositoryDummySpec extends UnitSpec{
  //TODO Add more methods as they are made
  "Calling getEntityList" should "return an array of the given entity type" in{
    //TODO Add to this with more entities as they are made
    val grd = new GenericRepositoryDummy
    val product = new Product
    val array:Array[Product] = grd.getEntityList(product)
    array.getClass.getSimpleName+"" should be ("Product[]")
  }
    "Calling findAll" should "return an array of the given entity type" in{
    //TODO Add to this with more entities as they are made
    val grd = new GenericRepositoryDummy
    val product = new Product
    val array:Array[Product] = grd.findAll(product)
    array.getClass.getSimpleName+"" should be ("Product[]")
  }
}