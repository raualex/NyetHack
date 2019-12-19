package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val player = Player()
    player.castFireball()

    //Aura
    val auraColor = player.auraColor()

    //Health Status
    val healthStatus = player.formatHealthStatus()

    //com.bignerdranch.nyethack.Player Status
    printPlayerStatus(player)
}

private fun printPlayerStatus(player: Player) {
    println(
        "(Aura: ${player.auraColor()})" +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
    )
    println("${player.name} ${player.formatHealthStatus()}")
}
