package converter.unit
import converter.unit.Unit

/**
 * Base unit for weight is grams
 */
class Weight(
    name: String,
    pluralName: String,
    symbols: Array<String>,
    conversionToGrams: Double
    ): Unit(name, pluralName, symbols, conversionToGrams) {

}