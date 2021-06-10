package world.cepi.level.events

import net.minestom.server.entity.Player
import net.minestom.server.event.trait.CancellableEvent
import net.minestom.server.event.trait.PlayerEvent

class XPChangeEvent(
    private val _player: Player,
    val oldXP: Int,
    var newXP: Int
): PlayerEvent, CancellableEvent {

    override fun getPlayer() = _player

    private var cancelled: Boolean = false

    override fun isCancelled() = cancelled

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

}