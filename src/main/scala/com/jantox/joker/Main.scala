package com.jantox.joker

object Main {

  trait Animal

  case class Dog(name: String) extends Animal

  implicit val showAnimal: Show[Animal] = new Show[Animal] {

    override def show(a: Animal): String = a match {
      case Dog(name) => s"Dog $name"
    }

  }

  def showDog(dog: Dog)(implicit show: Show[Dog]): Unit = {
    println(show.show(dog))
  }

  def main(args: Array[String]): Unit = {
    println("test")

    showDog(Dog("Jim"))
  }

}
