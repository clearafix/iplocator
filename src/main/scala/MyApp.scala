import java.net.InetAddress

import org.apache.commons.net.util.SubnetUtils

object MyApp extends App {

//  val input =
//   """|1.0.0.0/24,2151718,2077456,,0,0,3095,-37.7000,145.1833,1000
//      |1.0.1.0/24,1810821,1814991,,0,0,,26.0614,119.3061,50
//      |1.0.2.0/23,1810821,1814991,,0,0,,26.0614,119.3061,50
//      |1.0.4.0/22,2077456,2077456,,0,0,,-33.4940,143.2104,1000
//      |1.0.8.0/21,1809858,1814991,,0,0,,23.1167,113.2500,50
//      |1.0.16.0/20,1850147,1861060,,0,0,100-0001,35.6427,139.7677,500
//      |1.0.32.0/19,1809858,1814991,,0,0,,23.1167,113.2500,50""".stripMargin

//  import org.apache.commons.net.util.SubnetUtils._
//  val subnet = "192.168.0.3/31"
//  val utils = new SubnetUtils(subnet)
//  utils.getInfo.isInRange(address)

  val ipsCodes = io.Source.
    fromFile("resources/GeoLite2-City-Blocks-IPv4.csv")

  val cityCodes = io.Source.
    fromFile("resources/GeoLite2-City-Locations-en.csv")

  val ipsToGeoCodesTable = scala.collection.mutable.HashMap.empty[String,List[String]]
  val geCodesToCityTable = scala.collection.mutable.HashMap.empty[String,List[String]]

    for (line <- ipsCodes.getLines()) {
      if (line.contains("/1") || line.contains("/2") || line.contains("/3")) {

      } else {
        println(line)
      }
    }


//  println(s"SIZE ${ms.size}")
//  println(ObjectSizer.getObjectSize(ms))
//  println(s"KEY SET SIZE ${ms.keySet.size}")

//
//  time {
//    for (line <- ipsCodes.getLines()) {
//      val list = line.split(",").toList
//      val ipPair = list(0)
//      ipsToGeoCodesTable += (i(0) -> i)
//    }
//  }
//
//  time {
//    for (line <- cityCodes.getLines()) {
//      val i = line.split(",").toList
//      geCodesToCityTable += (i(0) -> i)
//    }
//  }

  val myIp = "255.0.0.0"

  val utils = new SubnetUtils("1.1.8.0/24")

  println(s"High: ${utils.getInfo.getHighAddress}")
  println(s"Low: ${utils.getInfo.getLowAddress}")
  println(s"Count: ${utils.getInfo.getAddressCountLong}")

  println(s"MY IP ${ipToLong(myIp)}")

  //  val objs = ips map (i => InetAddress.getByName(i))

  def time[R](block: => R): R = {
    val t0 = System.currentTimeMillis()
    val result = block    // call-by-name
    val t1 = System.currentTimeMillis()
    println("Elapsed time: " + (t1 - t0)/1000 + "s")
    result
  }


  def ipToLong(ipAddress: String): Long = {
    var result: Long = 0
    val ipAddressInArray = ipAddress.split("\\.")
    var i = 3
    for (i <- 1 to 3) {
      val ip: Long = ((ipAddressInArray(3 - i).toLong) << (i * 8))
      result = result + ip

    }
    result
  }

}