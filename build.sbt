lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-learing",
    version := "0.1.0",
    scalaVersion := "3.0.0-RC2-bin-20210323-d4f1c26-NIGHTLY",
    scalacOptions := Seq("-Yindent-colons", "-Yexplicit-nulls", "-Ysafe-init"),
    resolvers += Resolver.JCenterRepository
  )
