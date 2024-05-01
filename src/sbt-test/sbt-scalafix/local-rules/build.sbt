import _root_.scalafix.sbt.{BuildInfo => Versions}

inThisBuild(
  List(
    scalafixDependencies := List(
      // Custom rule cross-published to Maven Central https://github.com/scalacenter/example-scalafix-rule
      "ch.epfl.scala" %% "example-scalafix-rule" % "1.4.0"
    ),
    resolvers += Resolver.sonatypeRepo("snapshots"),
    scalaVersion := "2.13.11" // out of sync with scalafix.sbt.BuildInfo.scala213 on purpose
  )
)

val rules = project
  .disablePlugins(ScalafixPlugin)
  .settings(
    libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % Versions.scalafixVersion,
    libraryDependencies += "joda-time" % "joda-time" % "2.10.6"
  )

val service = project
  .dependsOn(rules % ScalafixConfig)
  .settings(
    libraryDependencies += "ch.epfl.scala" %% "example-scalafix-rule" % "3.0.0" % ScalafixConfig
  )

val sameproject = project
  .settings(
    libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % Versions.scalafixVersion % ScalafixConfig
  )
