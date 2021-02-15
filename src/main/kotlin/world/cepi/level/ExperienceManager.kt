package world.cepi.level

import it.unimi.dsi.fastutil.objects.Object2IntMap
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import net.minestom.server.entity.Player

object ExperienceManager {

    private val playerMap: Object2IntMap<Player> = Object2IntOpenHashMap()

    fun setExperience(player: Player, experience: Int) {
        playerMap[player] = experience.coerceIn(0..Int.MAX_VALUE)
        LevelDisplay.from(experience.coerceIn(0..Int.MAX_VALUE)).displayOnPlayer(player)
    }

    fun getExperience(player: Player): Int = playerMap.getInt(player)

    fun addExperience(player: Player, experience: Int) {
        setExperience(player, getExperience(player) + experience)
    }

    fun removeExperience(player: Player, experience: Int) {
        setExperience(player, getExperience(player) - experience)
    }

}