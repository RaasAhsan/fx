package com.jantox.sparses

import shapeless.{::, Generic, HNil}

object Main {

  final case class Data(a: String, b: String)

  def main(args: Array[String]): Unit = {
    val list = 1 :: 2 :: 3 :: "Hello" :: HNil

    val generic = Generic[Data]

    val data = Data("a", "b")

    val testHData = "a" :: "b" :: HNil

    val hlistParser = Parser.derive[Data]
    println(hlistParser.parse("1,2"))

    println(generic.from(testHData))

    val hData = generic.to(data)

    println(hData)
  }

}
