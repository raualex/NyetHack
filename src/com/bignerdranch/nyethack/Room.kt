package com.bignerdranch.nyethack

open class Room(val name: String) {
    protected open val dangerLevel = 5

    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel"

    open fun load() = "Nothing much to see here..."

    open fun ringBell() = "You cannot ring the bell unless you are in the Town Square."
}

open class TownSquare : Room("Town Square") {
    override val dangerLevel = super.dangerLevel - 3
    var bellSound = "GWONG"

    final override fun load() =
        "The villagers rally and cheer as you enter!\n${ringBell()} "

    override fun ringBell(): String = "The bell tower announces your arrival. $bellSound"
}
