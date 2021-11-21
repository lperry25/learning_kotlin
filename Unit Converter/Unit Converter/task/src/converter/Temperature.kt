package converter.unit
import converter.unit.Unit

/**
 * Base unit of Length is meters
 */
class Temperature(
    name: String,
    pluralName: String,
    symbols: Array<String>,
    conversionToMeters: Double
    ): Unit(name, pluralName, symbols, conversionToMeters) {

}