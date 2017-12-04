package fx

import fx.free.Coyoneda

object Main {

  import fx.syntax.monoid._
  import fx.syntax.eq._

  import fx.instances.string._
  import fx.instances.option._

  val listToOption = new (List ~> Option) {
    override def apply[A](f: List[A]): Option[A] = f.headOption
  }

  def main(args: Array[String]): Unit = {
    println("a" |+| "b")
    println("a" === "b")

    val coyo = Coyoneda.liftCoyoneda(List(3, 4, 5, 6, 7))
    val coyoB = coyo.map(_ + 1).map(_ * 10).map(_.toString).mapF(listToOption)

    println(Coyoneda.lowerCoyoneda(coyoB))

  }

}
