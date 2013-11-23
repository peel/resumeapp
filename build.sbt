name := "cv-generator"

version := "1.0-SNAPSHOT"

resolvers += "Local Play Repository" at "file:///usr/local/Cellar/play/2.2.0/libexec/repository/local/"

libraryDependencies ++= Seq(
  cache,
  "com.wordnik" % "swagger-play2_2.10" % "1.3.1",
  "io.github.cloudify" %% "spdf" % "1.0.0"
)

play.Project.playScalaSettings
