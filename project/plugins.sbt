// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.1")

// Web plugins
addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.3")
addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.7")
addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.1.0")
addSbtPlugin("org.irundaia.sbt" % "sbt-sassify" % "1.4.2")

// Play enhancer - this automatically generates getters/setters for public fields
// and rewrites accessors of these fields to use the getters/setters. Remove this
// plugin if you prefer not to have this feature, or disable on a per project
// basis using disablePlugins(PlayEnhancer) in your build.sbt
addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.1.0")

// Play Ebean support, to enable, uncomment this line, and enable in your build.sbt using
// enablePlugins(PlayEbean).
// addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "1.0.0")

// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % Option(System.getProperty("play.version")).getOrElse("2.5.1"))

addSbtPlugin("com.typesafe.sbt" %% "sbt-play-ebean" % "3.0.0")

// TODO: find a way to automatically load sbt plugins of projects we depend on
// if you see this and know how to do it, please open a pull request :)

// Uncomment the next line for local development of the Play Authentication core:
//addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")

// Uncomment the next line for local development of the Play Authentication core:
//addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.1.0")

// this is helpful plugin for figuring out dependencies
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.0")

addSbtPlugin("com.heroku" % "sbt-heroku" % "1.0.0")
