package world.cepi.level

import net.minestom.server.extensions.Extension
import world.cepi.actions.list.ActionManager
import world.cepi.kstom.Manager
import world.cepi.level.actions.ExperienceAction
import world.cepi.level.actions.LevelAction
import world.cepi.level.command.LevelCommand
import world.cepi.level.hooks.IncreaseLevelHook

class LevelExtension : Extension() {

    override fun initialize() {

        LevelCommand.register()
        IncreaseLevelHook.hook(eventNode)

        ActionManager.addAll(listOf(LevelAction::class, ExperienceAction::class))

        logger.info("[LevelExtension] has been enabled!")
    }

    override fun terminate() {

        LevelCommand.unregister()

        logger.info("[LevelExtension] has been disabled!")
    }

}