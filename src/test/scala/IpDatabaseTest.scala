import org.scalatest.{Matchers, WordSpec}

class IpDatabaseTest extends WordSpec with Matchers {

  val database = new IpDatabase()


  "Database" when {
    "initialized with default values " should {
      "have size 256" in {
        database.twoTopLevel.size should equal(256)
        database.twoTopLevel(0).size should equal(256)
        database.twoTopLevel(255).size should equal(256)
      }
    }
  }
}
