package world.cepi.level

import net.minestom.server.entity.Player
import org.jetbrains.annotations.Contract
import world.cepi.kepi.messages.sendFormattedTranslatableMessage

/**
 * An object representing a player's level/xp display.
 */
data class LevelDisplay(
    /** The level to display -- it is also the last level the (user) passed  */
    val level: Int,
    /** The whole number of XP to display. WIll turn into a number.*/
    val xp: Int
) {

    /**
     * The percentage to set the XP bar to.
     *
     * Current EQ: xp divided by (level to be passed's xp- level passed's xp), ex using the (level * 10) equation:
     * 5/(50-40)
     */
    val displayXP: Float by lazy {
        xp / (ExperienceManager.experienceRequiredFor(level + 1) - (ExperienceManager.experienceRequiredFor(level)).toFloat())
    }

    /**
     * Display the level and experience on a set player.
     *
     * @param player The player to display it on.
     */
    fun displayOnPlayer(player: Player) {
        player.level = level

        // Shouldn't happen but just in case
        if (!(0f..1f).contains(displayXP)) {
            player.sendFormattedTranslatableMessage("common", "error.internal")
            return
        }

        player.exp = displayXP
    }

    companion object {

        @Contract(pure = true)
        /**
         * Get a level display from an amount of experience
         * 
         * @param experience The amount of experience a user has.
         * 
         * @return A [LevelDisplay] corresponding to the image.
         */
        fun from(experience: Int): LevelDisplay {

            // Remove a level as that returns the next milestone required, not the current one.
            val level = ExperienceManager.nextLevelFromExperience(experience) - 1

            // Returns the total player's experience minus last level's requirements.
            return LevelDisplay(
                level.coerceIn(0..Int.MAX_VALUE), // coerce level
                (experience - ExperienceManager.experienceRequiredFor(level)).coerceIn(0..Int.MAX_VALUE) // experience - exp passed's level
            )
        }
    }
}