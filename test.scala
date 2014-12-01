import PayuScalapayment._

object test{
  
  val getVal = Payu.getPaymentHash("C0Dr8m","4000","test","test","test@gmail.com")
  
  println(getVal)
}