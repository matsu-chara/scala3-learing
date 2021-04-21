lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-learing",
    version := "0.1.0",
    scalaVersion := "3.0.1-RC1-bin-20210412-69108bf-NIGHTLY",
    scalacOptions := Seq("-Yindent-colons", "-Yexplicit-nulls", "-Ysafe-init"),
    resolvers += Resolver.JCenterRepository
  )
