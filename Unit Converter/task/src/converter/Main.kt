package converter

fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")
        try {
            var input = readln().lowercase()
            if (input == "exit") break
            val numberToConvert = input.substringBefore(" ").toDouble()
            input = input.substringAfter(" ")
            val (from, to) = input
                .split(" to ", " in ", " convertto ")
            val inputFrom = when (from.lowercase()) {
                "dc" -> "celsius"
                "df" -> "fahrenheit"
                else -> from.lowercase()
                    .replace("degrees ", "")
                    .replace("degree ", "")
            }
            val inputTo = when (to.lowercase()) {
                "dc" -> "celsius"
                "df" -> "fahrenheit"
                else -> to.lowercase()
                    .replace("degrees ", "")
                    .replace("degree ", "")
            }
            val base = options(inputFrom)
            val target = options(inputTo)
            if (base == null || target == null || base.type != target.type) {
                println(
                    "Conversion from ${base?.plural?.lowercase() ?: "???"} to " +
                            "${target?.plural?.lowercase() ?: "???"} is impossible"
                )
                println()
                continue
            } else {
                val conversionResult = convert(numberToConvert, base, target)
                when {
                    conversionResult < 0 && base.type == "wgt" -> println("Weight shouldn't be negative")
                    conversionResult < 0 && base.type == "dst" -> println("Length shouldn't be negative")
                    else -> {
                        println(
                            "$numberToConvert ${if (numberToConvert == 1.0) base.singular else base.plural}" +
                                    " is $conversionResult ${if (conversionResult == 1.0) target.singular else target.plural}"
                        )
                    }
                }
                println()
            }
        } catch (e: Exception) {
            println("Parse error")
            println()
            continue
        }
    }
}


fun options(input: String): MeasurementUnits? {
    MeasurementUnits.values().forEach {
        if (input == it.abbreviated.lowercase() || input == it.singular.lowercase() || input == it.name.lowercase()) {
            return it
        }
    }
    return null
}

fun convert(numberToConvert: Double, baseUnit: MeasurementUnits, targetUnit: MeasurementUnits): Double {
    when {
        baseUnit == targetUnit -> return numberToConvert
        baseUnit == MeasurementUnits.CELSIUS && targetUnit == MeasurementUnits.KELVINS -> {
            return MeasurementUnits.celsiusToKelvins(numberToConvert)
        }
        baseUnit == MeasurementUnits.KELVINS && targetUnit == MeasurementUnits.CELSIUS -> {
            return MeasurementUnits.kelvinsToCelsius(numberToConvert)
        }
        baseUnit == MeasurementUnits.FAHRENHEIT && targetUnit == MeasurementUnits.CELSIUS -> {
            return MeasurementUnits.fahrenheitToCelsius(numberToConvert)
        }
        baseUnit == MeasurementUnits.FAHRENHEIT && targetUnit == MeasurementUnits.KELVINS -> {
            return MeasurementUnits.fahrenheitToKelvins(numberToConvert)
        }
        baseUnit == MeasurementUnits.CELSIUS && targetUnit == MeasurementUnits.FAHRENHEIT -> {
            return MeasurementUnits.celsiusToFahrenheit(numberToConvert)
        }
        baseUnit == MeasurementUnits.KELVINS && targetUnit == MeasurementUnits.FAHRENHEIT -> {
            return MeasurementUnits.kelvinsToFahrenheit(numberToConvert)
        }
    }
    return (numberToConvert * baseUnit.convertedFromBase) / targetUnit.convertedFromBase
}
