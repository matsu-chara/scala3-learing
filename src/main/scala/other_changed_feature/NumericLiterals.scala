package other_changed_feature

import scala.language.experimental.genericNumberLiterals

object NumericLiterals:
  val x: Long = -10_000_000_000
  val y: BigInt = 0x123_abc_789_def_345_678_901
  val z: BigDecimal = 110_222_799_799.99

  val yy = (y: BigInt) match
    case 123_456_789_012_345_678_901 => ???

  case class BigFloat(mantissa: BigInt, exponent: Int):
    override def toString = s"${mantissa}e${exponent}"

  object BigFloat:
    import scala.util.FromDigits

    def apply(digits: String): BigFloat =
      val (mantissaDigits, givenExponent) =
        digits.toUpperCase.nn.split('E') match
          case Array(mantissaDigits, edigits) =>
            val expo =
              try FromDigits.intFromDigits(edigits)
              catch case ex: FromDigits.NumberTooLarge =>
                throw FromDigits.NumberTooLarge(s"exponent too large: $edigits")
            (mantissaDigits, expo)
          case Array(mantissaDigits) =>
            (mantissaDigits, 0)
      val (intPart, exponent) =
        mantissaDigits.split('.') match
          case Array(intPart, decimalPart) =>
            (intPart ++ decimalPart, givenExponent - decimalPart.length)
          case Array(intPart) =>
            (intPart, givenExponent)
      BigFloat(BigInt(intPart), exponent)

    given FromDigits.Floating[BigFloat] with
      def fromDigits(digits: String) = apply(digits)

// refined-autoみたいなマクロの例（コンパイル通らない） Cannot call macro method inline$fromDigitsImpl defined in the same source file
//    class FromDigits2 extends FromDigits.Floating[BigFloat]:
//      def fromDigits(digits: String) = apply(digits)
//  
//    given FromDigits2 with
//      override inline def fromDigits(digits: String) = ${
//        fromDigitsImpl('digits)
//      }
//
//      import scala.quoted.{Expr, Quotes}
//
//      private def fromDigitsImpl(digits: Expr[String])(using ctx: Quotes): Expr[BigFloat] =
//        digits.value match // リテラルで値取れる場合のみsome
//          case Some(ds) =>
//            try
//              val BigFloat(m, e) = FromDigits2.apply(ds)
//              '{BigFloat(${Expr(m)}, ${Expr(e)})}
//            catch case ex: FromDigits.FromDigitsException =>
//              //              ctx.error(ex.getMessage) // エラーにしたいがコンパイル通らない
//              '{throw new RuntimeException(ex.getMessage)}
//          case None =>
//            '{apply($digits)}
//  
//  
