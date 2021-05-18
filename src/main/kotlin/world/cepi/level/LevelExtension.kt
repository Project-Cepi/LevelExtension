package world.cepi.level

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.Manager
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.level.command.LevelCommand
import world.cepi.level.hooks.IncreaseLevelHook

class LevelExtension : Extension() {

    override fun initialize() {

        LevelCommand.register()
        Manager.connection.addPlayerInitialization(IncreaseLevelHook::hook)

        logger.info("[LevelExtension] has been enabled!")
    }

    override fun terminate() {

        LevelCommand.unregister()
        Manager.connection.removePlayerInitialization(IncreaseLevelHook::hook)

        logger.info("[LevelExtension] has been disabled!")
    }

}