package other_changed_feature

object ProgrammableStructualTypes:
  import scala.reflect.Selectable.reflectiveSelectable

  type Closeable = { def close(): Unit }

  class FileInputStream:
    def close(): Unit = ()

  class Channel:
    def close(): Unit = ()

  def autoClose(f: Closeable)(op: Closeable => Unit): Unit =
    try op(f) finally f.close()

  trait Vehicle extends reflect.Selectable:
    val wheels: Int
  
  val i3 = new Vehicle: // i3: Vehicle { val range: Int }
    val wheels = 4
    val range = 240
  
  i3.range