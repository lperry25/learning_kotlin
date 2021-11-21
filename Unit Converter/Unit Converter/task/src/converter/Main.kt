package converter
import converter.unit.Length
import converter.unit.Unit
import converter.unit.Weight
import converter.exception.DifferentUnitTypeException
import java.util.*

val wrongInput = "Wrong input"

val Meter = Length("meter", "meters", arrayOf("m"), 1.00)
val Kilometer = Length("kilometer", "kilometers", arrayOf("km"), 1000.00)
val Centimeter = Length("centimeter", "centimeters", arrayOf("cm"), 0.01)
val Millimeter = Length("millimeter", "millimeters", arrayOf("mm"), 0.001)
val Mile = Length("mile", "miles", arrayOf("mi"), 1609.35)
val Yard = Length("yard", "yards", arrayOf("yd"), 0.9144)
val Foot = Length("foot", "feet", arrayOf("ft", "\"", "\'\'"), 0.3048)
val Inch = Length("inch", "inches", arrayOf("in", "\'"), 0.0254)

val Gram = Weight("gram", "grams", arrayOf("g"), 1.00)
val Kilogram = Weight("kilogram", "kilograms", arrayOf("kg"), 1000.00)
val Milligram = Weight("milligram", "milligrams", arrayOf("mg"), 0.001)
val Pound = Weight("pound", "pounds", arrayOf("lb", "lbs"), 453.592)
val Ounce = Weight("ounce", "ounces", arrayOf("oz"), 28.3495)

fun getCorrectUnit(typedUnit: String): Unit{
    return when(typedUnit.lowercase()){
        Kilometer.name, Kilometer.pluralName, in Kilometer.symbols -> Kilometer
        Meter.name, Meter.pluralName, in Meter.symbols -> Meter
        Centimeter.name, Centimeter.pluralName, in Centimeter.symbols -> Centimeter
        Millimeter.name, Millimeter.pluralName, in Millimeter.symbols -> Millimeter
        Mile.name, Mile.pluralName, in Mile.symbols -> Mile
        Yard.name, Yard.pluralName, in Yard.symbols -> Yard
        Foot.name, Foot.pluralName, in Foot.symbols -> Foot
        Inch.name, Inch.pluralName, in Inch.symbols -> Inch
        Gram.name, Gram.pluralName, in Gram.symbols -> Gram
        Kilogram.name, Kilogram.pluralName, in Kilogram.symbols -> Kilogram
        Milligram.name, Milligram.pluralName, in Milligram.symbols -> Milligram
        Pound.name, Pound.pluralName, in Pound.symbols -> Pound
        Ounce.name, Ounce.pluralName, in Ounce.symbols -> Ounce
        else -> throw Exception(typedUnit)
    }
}

fun convertValueToAmount(amount: Double,firstUnit: String, secondUnit: String){
    try{
        var correctFirstUnit: Unit = getCorrectUnit(firstUnit);
        var correctSecondUnit: Unit = getCorrectUnit(secondUnit);

        if (
            correctFirstUnit is Length && correctSecondUnit is Length
            || correctFirstUnit is Weight && correctSecondUnit is Weight
        ){
            val convertedAmount: Double = correctFirstUnit.calculateAmountInNewUnit(amount, correctSecondUnit)
            println("$amount ${correctFirstUnit.getUnitTenseForValue(amount)} is $convertedAmount ${correctSecondUnit.getUnitTenseForValue(convertedAmount)}")
        } else{
            throw DifferentUnitTypeException("Conversion from ${correctFirstUnit.pluralName} to ${correctSecondUnit.pluralName} is impossible")
        }
    } catch (error: DifferentUnitTypeException) {
        println(error.message)
    } catch (error: Exception) {
        if(error.message.equals(firstUnit)){
            try{
                val unitOfSecond: Unit = getCorrectUnit(secondUnit)
                println("Conversion from ??? to ${unitOfSecond.pluralName} is impossible")
            }catch (error: Exception){
                println("Conversion from ??? to ??? is impossible")
            }

        } else{
            val unitOfFirst: Unit = getCorrectUnit(firstUnit)
            return println("Conversion from ${unitOfFirst.pluralName} to ??? is impossible")
        }
    }
}

fun main() {
    val reader = Scanner(System.`in`)
    while(true){
        println("Enter what you want to convert (or exit):")
        //println(readLine())
        val firstVal = reader.next()
        if (firstVal == "exit"){
            break
        }
        try{
            val amount:Double = firstVal.toDouble()
            var firstUnit:String = reader.next()
            // I don't care about the in between word
            reader.next()
            var secondUnit:String = reader.next()
            convertValueToAmount(amount, firstUnit, secondUnit)
            continue
        }catch(error: Exception){
            // do nothing with bad values
            continue
        }
    }
}
