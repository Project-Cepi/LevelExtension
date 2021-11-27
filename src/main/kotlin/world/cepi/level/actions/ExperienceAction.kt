package world.cepi.level.actions

import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import world.cepi.actions.Action
import world.cepi.level.ExperienceManager

@Serializable
data class ExperienceAction(val experienceAmount: Int): Action() {

    override fun invoke(source: Entity, target: Entity?) {
        (target as? Player)?.let {
            ExperienceManager.addExperience(it, experienceAmount)
        }
    }

}