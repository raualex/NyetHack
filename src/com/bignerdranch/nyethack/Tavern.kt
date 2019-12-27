package com.bignerdranch.nyethack

import java.io.File
import com.bignerdranch.nyethack.extensions.random

const val TAVERN_NAME = "Tearnyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")
val patronGold = mutableMapOf<String, Double>()
val readOnlyPatronList = patronList.toList()

private fun String.toDragonSpeak(): String = this.replace(Regex("[aeiouAEIOU]")) {
    when (it.value) {
        "a", "A" -> "4"
        "e", "E" -> "3"
        "i", "I" -> "1"
        "o", "O" -> "0"
        "u", "U" -> "|_|"
        else -> it.value
    }
}

fun main(args: Array<String>) {
    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }

    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }

    (0..9).forEach {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name
    }
    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(
            uniquePatrons.random(),
            menuList.random()
        )
        orderCount++
    }

    displayPatronBalances()
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurchase = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurchase - price
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)
    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath" || name == "DRAGON'S BREATH") {
        "$patronName exclaims: ${"Ah, delicious $name!".toDragonSpeak()}"
    } else {
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}