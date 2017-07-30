package utils

import org.scalacheck.Gen
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class IpUtilsPropertyTest extends PropSpec with Matchers with GeneratorDrivenPropertyChecks {

  implicit override val generatorDrivenConfig = PropertyCheckConfig(minSize = 1, maxSize = 200)

  property("should correctly parse IPs into array of bytes") {

    val as = for (a <- Gen.choose(0, 255)) yield a
    val bs = for (b <- as if b != 256) yield b

    forAll(as, bs, minSuccessful(256)) {
      (a, b) => {
        val ip = s"$a.$b.$a.$b"
        val ip2 = s"$a.$a.$a.$a"

        val expected = Array(a.toByte, b.toByte, a.toByte, b.toByte)
        val expected2 = Array.fill(4)(a.toByte)

        IpUtils.toByteArray(ip) should equal(expected)
        IpUtils.toByteArray(ip2) should equal(expected2)
      }
    }
  }
}
