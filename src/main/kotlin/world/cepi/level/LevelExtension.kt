package world.cepi.level

import net.minestom.server.extensions.Extension;

class LevelExtension : Extension() {

    override fun initialize() {
        logger.info("[LevelExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[LevelExtension] has been disabled!")
    }

}