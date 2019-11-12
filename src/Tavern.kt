import kotlin.math.roundToInt
const val TAVERN_NAME = "Tearnyl's Folly"

var playerGold = 10
var playerSilver = 10
var totalDragonsBreathGallons = 5.0
var totalDragonCoin = 5.0

fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
}

fun performPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + ( playerSilver / 100.0 )
//    println("Total Purse: $totalPurse")
//    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
//    println("Remaining Balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()

    if (remainingGold >= 0.0 || remainingSilver >= 0.0) {
        reduceGallonsInCask()
        playerGold = remainingGold
        playerSilver = remainingSilver
        displayBalance()
    } else {
        println("You do not have enough money to purchase another drink.")
    }
}

private fun toDragonCoin(totalGold: Double) {
    var newDragonCoin = totalDragonCoin - totalGold
}

private fun reduceGallonsInCask() {
    var reducedGallons = totalDragonsBreathGallons - 0.125
    totalDragonsBreathGallons = reducedGallons
    if (reducedGallons == 3.5) {
        println("A pint has been removed from the cask. Gallons left in cask: $totalDragonsBreathGallons")
    } else {
        println("A pint has been removed from the cask.")
    }
}

private fun displayBalance() {
//    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
    println("Player's purse balance: DragonCoin: $totalDragonCoin")
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

    performPurchase(price.toDouble())
    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath" || name == "DRAGON'S BREATH") {
        "Madrigal exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "Madrigal says: Thanks for the $name"
    }
    println(phrase)
}