package contextual

trait Codec[T]:
  def write(x: T): Unit

object Codec:
  given intCodec: Codec[Int] = ???

  given optionCodec[T](using ev: => Codec[T]): Codec[Option[T]] with
    def write(xo: Option[T]) = xo match
      case Some(x) => ev.write(x)
      case None =>
      val s = summon[Codec[Option[Int]]]
      s.write(Some(33))
      s.write(None)