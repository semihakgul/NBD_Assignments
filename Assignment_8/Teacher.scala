trait Teacher extends Employee {
  override def taxToPay: Unit = println("You should pay: " + salary * 0.1)
}
