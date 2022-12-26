import kotlin.random.Random

fun randomSharePrice() = Random.nextDouble(80.00, 100.00).twoDecimals()
private fun Double.twoDecimals() = String.format("%.2f", this).replace(",",".").toDouble()