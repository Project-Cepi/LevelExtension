package world.cepi.level.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.level.ExperienceManager

internal object LevelCommand: Kommand({

    val level by literal
    val xp by literal
    val info by literal

    val add by literal
    val set by literal
    val reset by literal
    val remove by literal

    val amount = ArgumentType.Integer("amount").min(0)

    applyHelp {
        """
            The level extension implements Cepi's <blue>experience system<gray>.
            
            There are two xp types: <blue>level & xp<gray>.
            
            Each XP type has the commands <yellow>add, set, reset, and remove
            
            For example,
            <yellow>/level xp add 5
            
            To see your levelling information run <yellow>/level info
            Or look at your experience bar.
        """.trimIndent()
    }

    syntax(info) {
        val player = sender as Player

        player.sendFormattedTranslatableMessage(
            "experience", "total",
            Component.text(ExperienceManager.getExperience(player), NamedTextColor.BLUE)
        )
    }

    onlyPlayers

    syntax(level, add, amount) {
        ExperienceManager.addLevels(player, !amount)
    }

    syntax(level, remove, amount) {
        ExperienceManager.removeExperience(player, ExperienceManager.experienceRequiredFor(!amount))
    }

    syntax(level, set, amount) {
        ExperienceManager.setExperience(player, ExperienceManager.experienceRequiredFor(!amount - 1))
    }

    syntax(reset) {
        ExperienceManager.setExperience(player, 0)
    }


    syntax(xp, add, amount) {
        ExperienceManager.addExperience(player, !amount)
    }

    syntax(xp, remove, amount) {
        ExperienceManager.removeExperience(player, !amount)
    }

    syntax(xp, set, amount) {
        ExperienceManager.setExperience(player, !amount)
    }

}, "level")