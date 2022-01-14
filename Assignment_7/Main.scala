import scala.annotation.tailrec
import scala.collection.Map

object Main{

  val days = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

  val products = Map("dog_food" -> 10, "fish_food" -> 5, "cat_food" -> 9)

  val someNumbers = List(1,2,3,4,5,6)

  val someRealNumbers = List( 1.2, 3, 3.443, 152.2, -12.3, 0, -0.342, -1312)

  def task1(subtaskOption: Char): String = {
    var res: String = ""

    if (subtaskOption == 'a')
      for (day <- days)
        res += day + ", "
    else if (subtaskOption == 'b')
      for (day <- days if day.toLowerCase.startsWith("s"))
        res += day + ", "
    else if (subtaskOption == 'c') {
      var i: Int = 0
      while (i < days.length) {
        res += days(i) + ", "
        i += 1
      }
    }
    return res.substring(0, res.length - 2)
  }

  def task2(l: List[String], reverse: Boolean = false): String = {
    if(l.length == 1 ) return l.head
    if (reverse)  return l.reverse.head + ", " + task2(l.reverse.tail)
    return l.head + ", " + task2(l.tail)
  }

  def task3(ll: List[String]): String = {
    @tailrec
    def task33(l: List[String],s:String): String = {
      if (l.tail.isEmpty)
        return s+l.head
      else   task33(l.tail,s+l.head + ", ")
    }
    task33(ll,"")
  }

  def task4(subtaskOption: Char): String = {
    var res: String = ""
    if (subtaskOption == 'a')
      res = days.foldLeft("")(_ + _ + ", ")
    else if (subtaskOption == 'b')
      res = days.foldRight("")(_ + ", " + _)
    else if (subtaskOption == 'c')
      res = days.foldRight("") { (next, sum) => if (next.startsWith("s") || next.startsWith("S") ) next + ", " + sum else sum }
    return res.substring(0, res.length - 2)
  }
  def task6(list: List[Int]): List[Int] = {
    return list.map(_ + 1)
  }

  def task7(list: List[Double]): List[Double] = {
    return list.filter(-5 < _).filter(_ < 12).map(_.abs)
  }

  def task8(tuple: (String, Boolean, Int)) = {
    println(tuple)
  }

  def task9(list: List[Double], res: List[Double]=List()):List[Double] = {

    if(list.isEmpty) return res
    if ( !list.head.toInt.equals(0)) return task9(list.tail, res:+list.head)
    return task9(list.tail, res)
  }

  def task10(product: String) = {
    val price: Option[Int] = products.get(product)
    println(price.getOrElse(s"Sorry, ${product} does not exist!"))
    if (price.nonEmpty && price.get>9 )
      println(s"Shipment is free for ${product}.")
  }
  def main(args: Array[String]): Unit = {
    println("Task 1:")
    println(task1('a'))
    println(task1('b'))
    println(task1('c'))
    println("Task 2:")
    println(task2(days, false))
    println(task2(days, true))
    println("Task 3:")
    println(task3(days))
    println("Task 4:")
    println(task4('a'))
    println(task4('b'))
    println(task4('c'))
    println("Task 5:")
    println(products.map { case (key, value) => (key, value * 0.9)})
    println("Task 6:")
    println(task6(someNumbers))
    println("Task 7:")
    println(task7(someRealNumbers))
    println("Task 8:")
    task8(Tuple3("test", false, 5))
    println("Task 9")
    println(task9(List(0,123,0,0.0,3)))
    println("Task 10")
    task10("dog_food")
    task10("deer_food")
  }

}

