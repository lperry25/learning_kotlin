package converter.unit

open class Unit(
    open val name: String,
    open val pluralName: String,
    open val symbols: Array<String>,
    open val conversionToBaseUnit: Double) {

    fun getUnitTenseForValue(double: Double): String{
        if (double.equals(1.00)) return this.name
        return this.pluralName
    }

    private fun calculateBaseUnit(amount: Double): Double{
        return amount * this.conversionToBaseUnit
    }

    fun calculateAmountInNewUnit(amount: Double, unit: Unit): Double{
        var amountInMeters = this.calculateBaseUnit(amount);
        return amountInMeters / unit.conversionToBaseUnit
    }

}