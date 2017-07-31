import utils.Timing

object MyApp extends App with Timing {

  val inputStr =
    """|1.0.0.0/24,2151718,2077456,,0,0,3095,-37.7000,145.1833,1000
       |1.0.1.0/24,1810821,1814991,,0,0,,26.0614,119.3061,50
       |1.0.2.0/23,1810821,1814991,,0,0,,26.0614,119.3061,50
       |1.0.4.0/22,2077456,2077456,,0,0,,-33.4940,143.2104,1000
       |1.0.8.0/21,1809858,1814991,,0,0,,23.1167,113.2500,50
       |1.0.16.0/20,1850147,1861060,,0,0,100-0001,35.6427,139.7677,500
       |1.0.32.0/19,1809858,1814991,,0,0,,23.1167,113.2500,50""".stripMargin


  val ipsCodes = io.Source.
    fromFile("src/main/resources/GeoLite2-City-Blocks-IPv4.csv")

  val cityCodes = io.Source.
    fromFile("src/main/resources/GeoLite2-City-Locations-en.csv")

  val ipsToGeoCodesTable = scala.collection.mutable.HashMap.empty[String, List[String]]
  val geCodesToCityTable = scala.collection.mutable.HashMap.empty[String, List[String]]

  millis {
    for (line <- ipsCodes.getLines()) {
      if (line.contains("/1") || line.contains("/2") || line.contains("/3")) {
      } else {
        println(line)
      }
    }
  }
}