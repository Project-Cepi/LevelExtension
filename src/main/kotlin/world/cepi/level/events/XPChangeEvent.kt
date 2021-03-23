package world.cepi.level.events

import net.minestom.server.entity.Player
import net.minestom.server.event.CancellableEvent
import net.minestom.server.event.PlayerEvent

class XPChangeEvent(
    player: Player,
    val oldXP: Int,
    var newXP: Int
): PlayerEvent(player), CancellableEvent {

    private var cancelled: Boolean = false

    override fun isCancelled() = cancelled

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

}