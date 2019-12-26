package com.bignerdranch.nyethack

import java.lang.IllegalStateException

fun main(args: Array<String>) {
    Game.play()
}

object Game {
    private var gameOver = false
    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room")))

    init {
        println("Welcome, adventurer.")
        player.castFireball()
    }

    fun play() {
        while(!gameOver) {
            println(currentRoom.description())
            println(currentRoom.load())

            //Player Status
            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()})" +
                    "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.toLowerCase()) {
            "move" -> move(argument)
            "exit" -> endGame()
            "quit" -> endGame()
            "map" -> map()
            "ring" -> currentRoom.ringBell()
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    private fun move(directionInput: String) =
            try {
                val direction = Direction.valueOf(directionInput.toUpperCase())
                val newPosition = direction.updateCoordinate(player.currentPosition)
                if(!newPosition.isInBounds) {
                    throw IllegalStateException("$direction is out of bounds.")
                }

                val newRoom = worldMap[newPosition.y][newPosition.x]
                player.currentPosition = newPosition
                currentRoom = newRoom
                "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
            } catch (e: Exception) {
                "Invalid direction: $directionInput."
            }

    private fun endGame() {
        gameOver = true
        println("Farewell, Adventurer!")
    }

    private fun map() {
        when (currentRoom.name) {
            "Town Square" -> println("X O O\nO O")
            "Tavern" -> println("O X O\nO O")
            "Back Room" -> println("O O X\nO O")
            "Long Corridor" -> println("O O O\nX O")
            "Generic Room" -> println("O O O\nO X")
        }
    }
}