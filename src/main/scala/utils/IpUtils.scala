package utils

object IpUtils {

  def toByteArray(ip: String): Array[Byte] = ip.split("\\.") map (_.toInt.toByte)

  def toLong(ip: String): Long = {
    val arr = ip.split("\\.") map (_.toLong)
    (arr(0) << 24) + (arr(1) << 16) + (arr(2) << 8) + arr(3)
  }

  def asString(arr: Array[Byte]): String = {
    arr map (x => x & 0xff) mkString(".")
  }

  def invMaskFromSuffix(bits:String): Array[Byte]  = {
    val mask = ~ (0xffffffff << (32 - bits.toByte))
    Array[Byte]((mask >>> 24).asInstanceOf[Byte], (mask >> 16 & 0xff).asInstanceOf[Byte], (mask >> 8 & 0xff).asInstanceOf[Byte], (mask & 0xff).asInstanceOf[Byte])
  }

  def maskFromSuffix(bits:String): Array[Byte]  = {
    val mask = 0xffffffff << (32 - bits.toByte)
    Array[Byte]((mask >>> 24).asInstanceOf[Byte], (mask >> 16 & 0xff).asInstanceOf[Byte], (mask >> 8 & 0xff).asInstanceOf[Byte], (mask & 0xff).asInstanceOf[Byte])
  }

  /**
    * Calculates broadcast IP address from CIDR notation string
    * @param prefix
    * @param suffix
    * @return
    */
  def getBroadcast(prefix: String, suffix:String): Array[Byte] = {
    val ip = toByteArray(prefix)
    val mask = invMaskFromSuffix(suffix)
    ip zip mask map (x => (x._1 | x._2).asInstanceOf[Byte])
  }

  def toBinaryString64(num: Long): String ={
    val string = num.toBinaryString
    val left = 64 - string.size
    val zeros = new String(Array.fill(left)('0'))
    new String(zeros) + string
  }
}