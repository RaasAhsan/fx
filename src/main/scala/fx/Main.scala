package fx

object Main {

  import fx.instances.monoid._
  import fx.syntax.monoid._

  def main(args: Array[String]): Unit = {
    println("a" |+| "b")
  }

}
