trait Employee extends PersonTask5 {
  private var sal: Double = _

  def salary = sal

  def salary_(s: Double): Unit = sal = s

  override def taxToPay: Unit = println("You should pay: " + sal * 0.2)
}
