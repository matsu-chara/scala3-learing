package contextual

import scala.concurrent.ExecutionContext

type Executable[T] = ExecutionContext ?=> T
