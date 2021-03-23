package world.cepi.level

import it.unimi.dsi.fastutil.objects.Object2IntMap
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import world.cepi.level.events.XPChangeEvent

object ExperienceManager {

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

    fun getExperience(player: Player): Int = playerMap.getInt(player)

    fun addExperience(player: Player, experience: Int) {
        setExperience(player, getExperience(player) + experience)
    }

    fun removeExperience(player: Player, experience: Int) {
        setExperience(player, getExperience(player) - experience)
    }

    /**
     * Gets the experience required from a [leve]
     *
     * @param level The level experience is extracted from
     *
     * @return The max amount of XP for that level.
     *
     * This is a total number, so it is the total number of experience.
     *
     * EX if the passed equation is level * 10,
     * The player will have to get 10 experience to progress to the next level.
     */
    fun maxExperienceOf(level: Int): Int {
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
    tailrec fun levelFromExperience(experience: Int, predictedLevel: Int = 1): Int {
        // If this level has more required experience than experience, make that the level.
        return if (maxExperienceOf(predictedLevel) > experience) predictedLevel
        // Else just recurse
        else levelFromExperience(experience, predictedLevel + 1)
    }

}