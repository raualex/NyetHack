package com.bignerdranch.nyethack
import com.bignerdranch.nyethack.extensions.frame

//fun Room.configurePitGoblin(block: Room.(Goblin) -> Goblin): Room {
//    val goblin = block(Goblin("Pit Goblin", description = "An Evil Pit Goblin"))
//    monster = goblin
//    return this
//}

open class Room(val name: String) {
    protected open val dangerLevel = 5
    var monster: Monster? = Goblin()

    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel\n" +
            "Creature: ${monster?.description ?: "none."}"

    open fun load() = "Nothing much to see here...".frame(5)
}

open class TownSquare : Room("Town Square") {
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "GWONG"

    final override fun load() =
        "The villagers rally and cheer as you enter!".frame(5) + "\n" + "${ringBell()}".frame(5)

    private fun ringBell() = "The bell tower announces your arrival. $bellSound"
}
