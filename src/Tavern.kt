const val TAVERN_NAME = "Tearnyl's Folly"

fun main(args: Array<String>) {
    placeOrder("shandy,DRAGON'S BREATH,5.91")
//    placeOrder("elixir,Shirley's Temple,4.12")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiouAEIOU]")) {
        when (it.value) {
            "a", "A" -> "4"
            "e", "E" -> "3"
            "i", "I" -> "1"
            "o", "O" -> "0"
            "u", "U" -> "|_|"
            else -> it.value
        }
    }

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price."
    println(message)

    val phrase = if (name == "Dragon's Breath" || name == "DRAGON'S BREATH") {
        "Madrigal exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "Madrigal says: Thanks for the $name"
    }
    println(phrase)
}