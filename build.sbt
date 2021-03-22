lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-learing",
    version := "0.1.0",
    scalaVersion := "3.0.0-RC1",
    scalacOptions := Seq("-Yindent-colons", "-Yexplicit-nulls"),
    resolvers += Resolver.JCenterRepository
  )
