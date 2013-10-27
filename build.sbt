name := "cv-generator"

version := "1.0-SNAPSHOT"

resolvers += "Local Play Repository" at "file:///usr/local/Cellar/play/2.2.0/libexec/repository/local/"

libraryDependencies ++= Seq(
  cache,
  "pdf" % "pdf_2.10" % "0.6"
)

play.Project.playScalaSettings
