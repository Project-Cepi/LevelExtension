package world.cepi.level

import net.minestom.server.entity.Player
import world.cepi.level.ExperienceManager

data class LevelDisplay(val level: Int, val xp: Int) {

    fun displayOnPlayer(player: Player) {
        player.level = level

        player.exp = (xp / (ExperienceManager.maxExperienceOf(level) - ExperienceManager.maxExperienceOf(level - 1)).toFloat())
    }

    companion object {

        /**
         * Get a level display from an amount of experience
         * 
         * @param experience The amount of experience a user has.
         * 
         * @return A [LevelDisplay] corresponding to the image.
         */
        fun from(experience: Int): LevelDisplay {

            val level = ExperienceManager.levelFromExperience(experience)

            return LevelDisplay(level, experience - ExperienceManager.maxExperienceOf(level - 1))
        }
    }
}