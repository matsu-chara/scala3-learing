package contextual

import scala.concurrent.Future

class Token(value: String)

given Conversion[String, Token] with
  def apply(str: String): Token = new Token(str)


object Completions:
  type HttpResponse = String
  type StatusCode = String

  enum CompletionArg:
    case Error(s: String)
    case Response(f: Future[HttpResponse])
    case Status(code: Future[StatusCode])

  object CompletionArg:
    given fromString: Conversion[String, CompletionArg] = Error(_)
    given fromFuture: Conversion[Future[HttpResponse], CompletionArg] = Response(_)
    given fromStatusCode: Conversion[Future[StatusCode], CompletionArg] = Status(_)

  // StringでもFuture[A]でもFuture[B]でもcompleteに渡せる。
  def complete[T](arg: CompletionArg) =
    import CompletionArg.*
    arg match
      case Error(s) => ???
      case Response(f) => ???
      case Status(code) => ???
