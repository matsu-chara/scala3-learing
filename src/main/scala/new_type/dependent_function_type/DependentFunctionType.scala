package new_type.dependent_function_type

trait Entry:
  type Key
  val key: Key

object Entry:
  def extractKey(e: Entry): e.Key = e.key
  
  //  (e: Entry) => e.Key と書けるようになった
  val extractor: (e: Entry) => e.Key = extractKey

  // 値としてfを渡せる
  def foo(e: Entry)(f: (e: Entry) => e.Key): e.Key = f(e)

  // (e: Entry) => e.Keyは下記のシンタックスシュガー
  val f = new Function1[Entry, Entry#Key]:
    def apply(e: Entry): e.Key = e.key