name := "iplocator"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies := Seq(
  "com.maxmind.geoip2" % "geoip2" % "2.9.0",
  "com.maxmind.db" % "maxmind-db" % "1.2.2",
  "org.scalatest" %% "scalatest" % "3.0.3" % "test",
  "commons-net" % "commons-net" % "3.6"
)