package converter

enum class MeasurementUnits(val abbreviated: String, val singular: String,
                         val plural: String, val type: String, val convertedFromBase: Double) {
    METERS("m", "meter", "meters", "dst", 1.0),
    KILOMETERS("km", "kilometer", "kilometers", "dst", 1000.0),
    CENTIMETERS("cm", "centimeter", "centimeters", "dst", 0.01),
    MILLIMETERS("mm", "millimeter", "millimeters", "dst", 0.001),
    MILES("mi", "mile", "miles", "dst", 1609.35),
    YARDS("yd", "yard", "yards", "dst", 0.9144),
    FEET("ft", "foot", "feet", "dst", 0.3048),
    INCHES("in", "inch", "inches", "dst", 0.0254),
    GRAMS("g", "gram", "grams", "wgt", 1.0),
    KILOGRAMS("kg", "kilogram", "kilograms", "wgt", 1000.0),
    MILLIGRAMS("mg", "milligram", "milligrams", "wgt", 0.001),
    POUNDS("lb", "pound", "pounds", "wgt", 453.592),
    OUNCES("oz", "ounce", "ounces", "wgt", 28.3495),
    FAHRENHEIT("f", "degree Fahrenheit", "degrees Fahrenheit", "temp", 0.0),
    CELSIUS("c", "degree Celsius", "degrees Celsius", "temp", 0.0),
    KELVINS("k", "kelvin", "kelvins", "temp", 0.0);

    companion object {
        fun celsiusToFahrenheit(c: Double): Double = (c * (9.0/5.0)) + 32.0
        fun kelvinsToFahrenheit(k: Double): Double = (k * (9.0/5.0)) - 459.67
        fun fahrenheitToCelsius(f: Double): Double = (f - 32) * (5.0/9.0)
        fun fahrenheitToKelvins(f: Double): Double = (f + 459.67) * (5.0/9.0)
        fun celsiusToKelvins(c: Double): Double = c + 273.15
        fun kelvinsToCelsius(k: Double): Double = k - 273.15
    }
}