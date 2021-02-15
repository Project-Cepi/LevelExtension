package world.cepi.level

import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import world.cepi.kstom.asRich

data class LevelDisplay(val level: Int, val xp: Int) {

    fun displayOnPlayer(player: Player) {
        player.level = level

        player.exp = (xp / (getMaxExperience(level) - getMaxExperience(level - 1)).toFloat())
    }

    companion object {

        /**
         * Gets the experience required from a [leve]
         *
         * @param level The level experience is extracted from
         *
         * @return The max amount of XP for that level.
         */
        fun getMaxExperience(level: Int): Int {
            return level.coerceIn(0..Int.MAX_VALUE) * 10
        }

        private tailrec fun levelFromExperience(experience: Int, predictedLevel: Int = 1): Int {
            // If this level has more required experience than experience, make that the level.
            return if (getMaxExperience(predictedLevel) > experience) predictedLevel
            // Else just recurse
            else levelFromExperience(experience, predictedLevel + 1)
        }

        fun from(experience: Int): LevelDisplay {

            val level = levelFromExperience(experience)

            return LevelDisplay(level, experience - getMaxExperience(level - 1))
        }
    }
}