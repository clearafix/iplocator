package utils

trait Timing {

  def millis[T](code: => T): T = {
    val t0 = System.currentTimeMillis()
    val result = code
    val t1 = System.currentTimeMillis()
    println("Elapsed time: " + (t1 - t0) + "ms")
    result
  }

  def nanos[T](code: => T): T = {
    val t0 = System.nanoTime()
    val result = code
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }
}
