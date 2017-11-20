package fx

object Main {

  import fx.syntax.monoid._
  import fx.syntax.eq._
  
  import fx.instances.string._

  def main(args: Array[String]): Unit = {
    println("a" |+| "b")
    println("a" === "b")
  }

}
