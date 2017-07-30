package utils

import org.scalatest.{Matchers, WordSpec}

class IpUtilsTest extends WordSpec with Matchers {
  "IpUtilsTest" should {
    "should parse IP to byte array correctly" in {
      val ip = "0.120.130.255"
      val expected = Array[Byte](0, 120, 130.toByte, 255.toByte)

      IpUtils.toByteArray(ip) should equal(expected)
    }

    "should parse IP to long correctly" in {
      val ip0 = "0.0.0.0"
      val ip1 = "0.0.0.255"
      val ip2 = "0.0.255.255"
      val ip3 = "0.255.255.255"
      val ip4 = "255.255.255.255"

      IpUtils.toLong(ip0) should equal(0L)
      IpUtils.toLong(ip1) should equal(255L)
      IpUtils.toLong(ip2) should equal(65535L)
      IpUtils.toLong(ip3) should equal(16777215L)
      IpUtils.toLong(ip4) should equal(4294967295L)
    }
  }
}
