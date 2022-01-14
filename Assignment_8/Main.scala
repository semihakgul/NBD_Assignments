import scala.annotation.tailrec
import scala.collection.Map

object Main {
  val days = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
  val weekdays = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
  val weekends = List("Saturday", "Sunday")

  val products = Map("clothes" -> 1000, "shoes" -> 500)

  def task1(day: String): String = day match {
    case day if( weekdays.map(_.toLowerCase()).contains(day.toLowerCase())) =>  "work"
    case day if( weekends.map(_.toLowerCase()).contains(day.toLowerCase())) =>  "weekend"
    case _ => "no such day"
  }

  def task4(a: Int, f: Int => Int): Int = {
    f(f(f(a)))
  }

  def main(args: Array[String]): Unit = {

    println("Task 1:")
    println(task1("monday"))
    println(task1("saturday"))
    println(task1("dreamday"))

    println("Task2:")
    println("Bank 1 (with no initial balance):")
    val bank1: BankAccount = new BankAccount()
    bank1.currentBalance
    bank1.deposits(6014)
    bank1.currentBalance
    bank1.withdraw(14)
    bank1.currentBalance
    println("Bank 2 (with initial balance):")
    val bank2 = new BankAccount(1000)
    bank2.currentBalance

    println("Task 3:")
    val peter: Person = new Person("Mat", "Smith")
    peter.greet(new Person("Semih", "Akgul"))
    peter.greet(new Person("John", "Smith"))
    peter.greet(new Person("Isimsiz", "Adam"))


    println("Task 4 (adds 1 to n 4 times):")
    println(task4( 2, n => n + 1))

    println("Task5:")
    object person1 extends PersonTask5("Semih", "Akgul") with Student
    System.out.println("Student tax:")
    person1.taxToPay
    object person2 extends PersonTask5("Ali", "Algul") with Teacher
    System.out.println("Teacher tax:")
    person2.salary_(3500)
    person2.taxToPay
    object person3 extends PersonTask5("Veli", "Sarigul") with Employee
    System.out.println("Employee tax:")
    person3.salary_(3500)
    person3.taxToPay
    object person4 extends PersonTask5("Ayse", "Yilmaz") with Employee with Student
    System.out.println("Employee and Student tax:")
    person4.salary_(1000)
    person4.taxToPay
    object person5 extends PersonTask5("Mehmet", "Aslan") with Student with Employee
    System.out.println("Student and Employee tax:")
    person5.salary_(1000)
    person5.taxToPay
  }
}

class BankAccount(private var balance: Double) {

  def this() = {
    this(0)
  }

  def deposits(amount: Double): Unit = {
    if (amount > 0) balance += amount
    else System.out.println("Amount can not be negative or zero!")
  }

  def withdraw(amount: Double): Unit = {
    if (amount > 0  && amount <= balance) balance -= amount
    else System.out.println("Amount can not be negative or zero and it should be greater than the balance!")
  }

  def currentBalance: Unit = {
    println("Your balance is: " + balance)
  }
}

case class Person(var firstName: String, var lastName: String) {
  def greet(person: Person): Unit = person match {
    case Person("John", "Smith") => println("Hi, John Smith! I am not sure if that is your real name or a joke... :S")
    case Person(_, "Akgul") => println("Hi, Mr. Akgul! What a nice surname indeed!")
    case Person(name, surname)     => println(s"Welcome, $name $surname!")
  }
}
