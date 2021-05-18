package world.cepi.level.hooks

import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Player
import net.minestom.server.sound.SoundEvent
import world.cepi.level.events.XPChangeEvent
import world.cepi.kstom.addEventCallback
import world.cepi.kstom.util.component1
import world.cepi.kstom.util.component2
import world.cepi.kstom.util.component3
import world.cepi.level.ExperienceManager

internal object IncreaseLevelHook {

    private val levelEventHook = fun(event: XPChangeEvent) = with(event) {
        // We're looking for XP going up, not down
        if (oldXP >= newXP) return

        val oldLevel = ExperienceManager.nextLevelFromExperience(oldXP)
        val newLevel = ExperienceManager.nextLevelFromExperience(newXP)

        // Looking for level increases
        if (oldLevel >= newLevel) return

        val player = event.player

        // TODO translations
        player.sendMessage(
            Component.text("+ ", NamedTextColor.GREEN)
                .append(Component.text("Level ", NamedTextColor.GRAY))
                .append(Component.text("(", NamedTextColor.DARK_GRAY))
                .append(Component.text(oldLevel - 1, NamedTextColor.GRAY))
                .append(Component.text(" -> ", NamedTextColor.GRAY))
                .append(Component.text(newLevel - 1, NamedTextColor.GREEN))
                .append(Component.text(")", NamedTextColor.DARK_GRAY))
        )

        val (x, y, z) = player.position

        player.playSound(Sound.sound(
            SoundEvent.ENTITY_PLAYER_LEVELUP,
            Sound.Source.MASTER,
            2f,
            1.5f
        ), x, y, z)

    }

    fun hook(player: Player) {
        player.addEventCallback(levelEventHook)
    }

}