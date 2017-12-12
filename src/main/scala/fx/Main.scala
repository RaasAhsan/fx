package fx

import fx.free.Free

object Main {

//  import fx.syntax.monoid._
//  import fx.syntax.eq._
//
//  import fx.instances.string._
//  import fx.instances.option._
//
//  val listToOption = new (List ~> Option) {
//    override def apply[A](f: List[A]): Option[A] = f.headOption
//  }

  sealed trait Command[A]
  final case class Say(message: String) extends Command[Unit]
  final case class Ask() extends Command[String]

  import Free.FreeC

  type CommandF[A] = FreeC[Command, A]

  def say(message: String): CommandF[Unit] = {
    Free.liftFC(Say(message))
  }

  def ask(): CommandF[String] = {
    Free.liftFC(Ask())
  }

  def main(args: Array[String]): Unit = {
    val effect = for {
      _    <- say("Welcome!")
      _    <- say("Please enter your name: ")
      name <- ask()
    } yield name

    println(effect)

//    println("a" |+| "b")
//    println("a" === "b")
//
//    val coyo = Coyoneda.liftCoyoneda(List(3, 4, 5, 6, 7))
//    val coyoB = coyo.map(_ + 1).map(_ * 10).map(_.toString).mapF(listToOption)
//
//    println(Coyoneda.lowerCoyoneda(coyoB))

  }

}
