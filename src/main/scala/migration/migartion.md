## classpath level

- scala2ではpickleだったsignatureだがscala3ではtasty
- scala3コンパイラはpickleも読める

## scala3 から scala2.13ライブラリに依存

```scala
lazy val foo = project.in.file("foo")
.settings(scalaVersion := "3.0.0-RC2")
.dependsOn(bar)

lazy val bar = project.in(file("bar"))
.settings(scalaVersion := "2.13.5)

// 外部ライブラリんら以下
lazy val foo = project.in.file("foo")
  .settings(
    scalaVersion := "3.0.0-RC2",
    libraryDependencies += ("org.bar" %% "bar" % "1.0.0").cross(CrossVersion.for3Use2_13)
  )
```

`CrossVersion.for3Use2_13` すると bar_3の代わりにbar_2.13が解決される。

scala3の標準コレクションライブラリはscala2.13でコンパイルされている

## scala2.13向けのtastyリーダー

様々な機能が使えるが一部例外がある

## macro

scala 3のマクロはscala 2.13と互換性がない

- scala 3からマクロを使っているscala 2.13モジュールに直接は依存できない
- scala 2.13のマクロでコンパイルした成果物(jar)はscala3で使える
- 逆も同じ

## scalac

-source:3.0-migrationをするとエラーが警告などになりマイグレーションしやすくなる

-source:3.0-migrationと-rewriteをつけてコンパイルするとほとんどの警告が自動で修正される。（コンパイルが通る状態でないとリライトは発生しない）
-source:3.0-migrationと-explainや-explain-typesをつけるとエラーの詳細がでる（マイグレーションオプションなくてもこの辺は使える）


