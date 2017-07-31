package utils

import org.scalatest.{Matchers, WordSpec}
import utils.IpUtils._

class IpUtilsTest extends WordSpec with Matchers  with Timing{
  "IpUtilsTest" should {
    "should parse IP to byte array correctly" in {
      val ip = "0.120.130.255"
      val expected = Array[Byte](0, 120, 130.toByte, 255.toByte)

      toByteArray(ip) should equal(expected)
    }
    "should parse IP to long from strings correctly" in {
      val ip0 = "0.0.0.0"
      val ip1 = "0.0.0.255"
      val ip2 = "0.0.255.255"
      val ip3 = "0.255.255.255"
      val ip4 = "255.255.255.255"
      val ip5 = "223.255.231.192"
      val ip6 = "223.255.231.255"

      IpUtils.toLong(ip0) should equal(0L)
      IpUtils.toLong(ip1) should equal(255L)
      IpUtils.toLong(ip2) should equal(65535L)
      IpUtils.toLong(ip3) should equal(16777215L)
      IpUtils.toLong(ip4) should equal(4294967295L)
      toLong(ip5) should equal (3758090176L)
      toLong(ip6) should equal (3758090239L)
    }
    "should parse IP to long from byte arrays correctly" in {
      val ip0 = toByteArray("0.0.0.0")
      val ip1 = toByteArray("0.0.0.255")
      val ip2 = toByteArray("0.0.255.255")
      val ip3 = toByteArray("0.255.255.255")
      val ip4 = toByteArray("255.255.255.255")
      val ip5 = toByteArray("223.255.231.192")
      val ip6 = toByteArray("223.255.231.255")

      IpUtils.toLong(ip0) should equal(0L)
      IpUtils.toLong(ip1) should equal(255L)
      IpUtils.toLong(ip2) should equal(65535L)
      IpUtils.toLong(ip3) should equal(16777215L)
      IpUtils.toLong(ip4) should equal(4294967295L)
      toLong(ip5) should equal (3758090176L)
      toLong(ip6) should equal (3758090239L)
    }
    "should return broadcast(max) IP address for subnet" in {
      val ip0 = "1.0.0.0/24"
      asString(getBroadcast("1.0.0.0", "24")) should equal ("1.0.0.255")
      asString(getBroadcast("1.0.0.0", "12")) should equal ("1.15.255.255")
      asString(getBroadcast("1.0.0.0","8")) should equal ("1.255.255.255")
      asString(getBroadcast("1.0.0.0", "31")) should equal ("1.0.0.1")
      asString(getBroadcast("56.128.0.0", "9")) should equal ("56.255.255.255")
      asString(getBroadcast("223.255.231.192", "26")) should equal ("223.255.231.255")

    }
    "should return pair of longs (minIp, maxIp) from CIDR subnet" in {
      val result = subnetToRangeLongs("223.255.231.192/26")
      result should equal ( (toLong("223.255.231.192"), toLong("223.255.231.255")) )
    }
  }
}
