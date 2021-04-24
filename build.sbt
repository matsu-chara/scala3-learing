lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-learning",
    version := "0.1.0",
    scalaVersion := "3.0.1-RC1-bin-20210423-e012734-NIGHTLY",
    scalacOptions := Seq("-Yindent-colons", "-Yexplicit-nulls", "-Ysafe-init"),
    resolvers += Resolver.JCenterRepository
  )
