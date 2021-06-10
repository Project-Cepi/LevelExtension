package world.cepi.level

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.Manager
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.kstom.extension.ExtensionCompanion
import world.cepi.level.command.LevelCommand
import world.cepi.level.hooks.IncreaseLevelHook

class LevelExtension : Extension() {

    override fun initialize() {

        LevelCommand.register()
        IncreaseLevelHook.hook(eventNode)

        logger.info("[LevelExtension] has been enabled!")
    }

    override fun terminate() {

        LevelCommand.unregister()

        logger.info("[LevelExtension] has been disabled!")
    }

    companion object: ExtensionCompanion<LevelExtension>(LevelExtension::class)

}