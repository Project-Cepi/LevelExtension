package world.cepi.level.actions

import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import world.cepi.actions.Action
import world.cepi.level.ExperienceManager

class ExperienceAction(val experienceAmount: Int): Action() {

    override fun invoke(source: Entity, target: Entity?) {
        (target as? Player)?.let {
            ExperienceManager.addExperience(it, experienceAmount)
        }
    }

}