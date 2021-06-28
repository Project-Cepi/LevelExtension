package world.cepi.level.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal
import world.cepi.level.ExperienceManager

internal object LevelCommand: Command("level") {

    init {

        val level = "level".literal()
        val xp = "xp".literal()
        val info = "info".literal()

        val add = "add".literal()
        val set = "set".literal()
        val reset = "reset".literal()
        val remove = "remove".literal()

        val amount = ArgumentType.Integer("amount").min(0)

        addSyntax(info) {
            val player = sender as Player

            player.sendFormattedTranslatableMessage(
                "experience", "total",
                Component.text(ExperienceManager.getExperience(player), NamedTextColor.BLUE)
            )
        }

        addSyntax(level, add, amount) {
            val player = sender as Player
            ExperienceManager.addExperience(player, ExperienceManager.experienceRequiredFor(context.get(amount)))
        }

        addSyntax(level, remove, amount) {
            val player = sender as Player
            ExperienceManager.removeExperience(player, ExperienceManager.experienceRequiredFor(context.get(amount)))
        }

        addSyntax(level, set, amount) {
            val player = sender as Player
            ExperienceManager.setExperience(player, ExperienceManager.experienceRequiredFor(context.get(amount) - 1))
        }

        addSyntax(reset) {
            val player = sender as Player
            ExperienceManager.setExperience(player, 0)
        }


        addSyntax(xp, add, amount) {
            val player = sender as Player
            ExperienceManager.addExperience(player, context.get(amount))
        }

        addSyntax(xp, remove, amount) {
            val player = sender as Player
            ExperienceManager.removeExperience(player, context.get(amount))
        }

        addSyntax(xp, set, amount) {
            val player = sender as Player
            ExperienceManager.setExperience(player, context.get(amount))
        }


    }

}