package world.cepi.level

import net.minestom.server.extensions.Extension
import world.cepi.actions.list.ActionManager
import world.cepi.kstom.Manager
import world.cepi.kstom.util.log
import world.cepi.kstom.util.node
import world.cepi.level.actions.ExperienceAction
import world.cepi.level.actions.LevelAction
import world.cepi.level.command.LevelCommand
import world.cepi.level.hooks.IncreaseLevelHook

class LevelExtension : Extension() {

    override fun initialize(): LoadStatus {

        LevelCommand.register()
        IncreaseLevelHook.hook(node)

        ActionManager.add(LevelAction::class, ExperienceAction::class)

        log.info("[LevelExtension] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {

        LevelCommand.unregister()

        log.info("[LevelExtension] has been disabled!")
    }

}