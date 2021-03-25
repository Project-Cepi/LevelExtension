package world.cepi.level

import net.minestom.server.MinecraftServer
import net.minestom.server.extensions.Extension;
import org.slf4j.Logger

class LevelExtension : Extension() {

    override fun initialize() {

        MinecraftServer.getCommandManager().register(LevelCommand)

        logger.info("[LevelExtension] has been enabled!")
    }

    override fun terminate() {

        MinecraftServer.getCommandManager().unregister(LevelCommand)

        logger.info("[LevelExtension] has been disabled!")
    }

}