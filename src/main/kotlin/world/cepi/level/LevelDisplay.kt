package world.cepi.level

import net.minestom.server.entity.Player

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

        /**
         * Gets a level from an amount of experience.
         *
         * @param experience The experience to use as a backer.
         * @param predictedLevel The level the algorithm can check against.
         *          It can't go higher than the actual level,
         *          though the closer it is to the actual level,
         *          the less calculations have to be performed.
         *
         * @return The level this experience is at.
         */
        private tailrec fun levelFromExperience(experience: Int, predictedLevel: Int = 1): Int {
            // If this level has more required experience than experience, make that the level.
            return if (getMaxExperience(predictedLevel) > experience) predictedLevel
            // Else just recurse
            else levelFromExperience(experience, predictedLevel + 1)
        }

        /**
         * Get a level display from an amount of experience
         * 
         * @param experience The amount of experience a user has.
         * 
         * @return A [LevelDisplay] corresponding to the image.
         */
        fun from(experience: Int): LevelDisplay {

            val level = levelFromExperience(experience)

            return LevelDisplay(level, experience - getMaxExperience(level - 1))
        }
    }
}