package world.cepi.level

import it.unimi.dsi.fastutil.objects.Object2IntMap
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import net.minestom.server.entity.Player
import world.cepi.level.events.XPChangeEvent
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToInt

object ExperienceManager {

    /** Internal map to store XP for a player. */
    private val playerMap: Object2IntMap<Player> = Object2IntOpenHashMap()

    /**
     * Sets the experience of a player
     *
     * @param player The player to set the experience of
     * @param experience The experience to set the player at
     */
    fun setExperience(player: Player, experience: Int) {

        val event = XPChangeEvent(
            player,
            // Current experience of the player
            playerMap.getInt(player),
            // New experience of the player
            experience
        )

        player.callCancellableEvent(XPChangeEvent::class.java, event) {

            val coercedExperience = event.newXP.coerceIn(0..Int.MAX_VALUE)

            playerMap[player] = coercedExperience
            LevelDisplay.from(coercedExperience).displayOnPlayer(player)

        }
    }

    /**
     * Get the amount of experience a player has.
     *
     * @param player The player to get it from
     *
     * @return The amount of experience that player has.
     */
    fun getExperience(player: Player): Int = playerMap.getInt(player)

    /**
     * Adds an amount of experience to a player
     *
     * @param player The player to add the experience to.
     * @param experience The amount of experience to add.
     */
    fun addExperience(player: Player, experience: Int) =
        setExperience(player, getExperience(player) + experience)

    /**
     * Removes an amount of experience from a player
     *
     * @param player The player to remove the experience from.
     * @param experience The amount of experience to remove.
     */
    fun removeExperience(player: Player, experience: Int) =
        setExperience(player, getExperience(player) - experience)

    /**
     * Gets the experience required from a [level]
     *
     * @param level The level experience is extracted from
     *
     * @return The max amount of XP for that level.
     *
     * This is a total number, so it is the total number of experience.
     *
     * EX if the passed equation is level * 10,
     * The player will have to get 10 experience to progress to the next level.
     *
     * Level 1 needs to have an amount
     *
     * EX:
     * Level 1: 10
     * Level 2: 20
     */
    fun experienceRequiredFor(level: Int): Int {
        return (0.04 * (level.toDouble().pow(3)) + 0.8 * (level.toDouble().pow(2)) + 2 * level).roundToInt()
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
     * @return The next level this experience needs to achieve.
     */
    tailrec fun nextLevelFromExperience(experience: Int, predictedLevel: Int = 1): Int {
        // If this level has more required experience than experience provided, return that
        return if (experienceRequiredFor(predictedLevel) > experience) predictedLevel
        // Else just recurse
        else nextLevelFromExperience(experience, predictedLevel + 1)
    }

}