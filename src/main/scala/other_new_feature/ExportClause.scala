package other_new_feature

object ExportClause:
  class BitMap
  class InkJet
  
  class Printer:
    type PrinterType
    def print(bits: BitMap): Unit = ???
    def status: List[String] = ???
  
  class Scanner:
    def scan(): BitMap = ???
    def status: List[String] = ???
  
  class Copier:
    private val printUnit = new Printer { type PrinterType = InkJet }
    private val scanUnit = new Scanner
  
    export scanUnit.scan
    export printUnit.{status => _, _}
  
    def status: List[String] = printUnit.status ++ scanUnit.status

  val copier = new Copier
  copier.print(copier.scan())