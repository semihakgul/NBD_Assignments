class ContainerClass[A](value: A) {
  private var _value: A = value
  def getContent: A = _value
  def applyFunction(func: A => A): A =
  {
    _value = func(_value)
    _value
  }
}
trait Maybe[A]
class No extends Maybe[Nothing]
class Yes[A](value: A) extends Maybe[A] {
  private var _value: A = value
  def getContent: A = _value
}

class Task3[A](value: A) {
  private var _value: A = value
  def getContent: A = _value

  def applyFunction(f: A => A): A =
  {
    if (f(_value).isInstanceOf[No]) _value
    else if (f(_value).isInstanceOf[Yes[_]]) {
      _value = f(_value).asInstanceOf[A]
      _value
    } else null.asInstanceOf[A];
  }
}

class Task4[A](value: A) {
  private var _value: A = value

  def getContent: A = _value

  def getOrElse[B]: B = {
    if (_value.isInstanceOf[No])
       "This is class NO with no content".asInstanceOf[B]
    else if (_value.isInstanceOf[Yes[_]])
       _value.asInstanceOf[Yes[A]].getContent.asInstanceOf[B]
    else
       null.asInstanceOf[B];
  }
}
object Main {

  def main(args: Array[String]): Unit = {

    println("Task 1:")
    val task1: ContainerClass[Int] = new ContainerClass(500)
    task1.applyFunction(a => a + a)
    println(task1.getContent)

    println("Task 2:")
    var no: No = new No()
    println(no.isInstanceOf[Maybe[_]])
    var yes: Yes[String] = new Yes("some value")
    println(yes.isInstanceOf[Maybe[_]])

    println("Task 3:")
    val task3ForNo: Task3[No] = new Task3[No](new No())
    task3ForNo.applyFunction(a => a)
    println(task3ForNo.getContent)
    val task3ForYes: Task3[Yes[String]] = new Task3[Yes[String]](new Yes("some other value"))
    task3ForYes.applyFunction(a => new Yes(a.getContent + 1))
    println(task3ForYes.getContent.getContent)

    println("Task 4:")
    val task4ForNo: Task4[No] = new Task4[No](new No())
    println(task4ForNo.getOrElse)
    val task4ForYes: Task4[Yes[String]] = new Task4[Yes[String]](new Yes("some other value for task 4"))
    println(task4ForYes.getOrElse)
  }
}



