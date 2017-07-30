package utils

object IpUtils {

  def toByteArray(ip: String): Array[Byte] = ip.split("\\.") map (_.toInt.toByte)

  def toLong(ip: String): Long = {
    val arr = ip.split("\\.") map (_.toLong)
    (arr(0) << 24) + (arr(1) << 16) + (arr(2) << 8) + arr(3)
  }
}
