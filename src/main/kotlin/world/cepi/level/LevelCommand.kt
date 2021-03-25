package world.cepi.level

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.asSubcommand

object LevelCommand: Command("level") {

    init {

        val level = "level".asSubcommand()
        val xp = "xp".asSubcommand()
        val info = "info".asSubcommand()

        val add = "add".asSubcommand()
        val set = "set".asSubcommand()
        val reset = "reset".asSubcommand()
        val remove = "remove".asSubcommand()

        val amount = ArgumentType.Integer("amount").min(0)

        addSyntax(info) { sender ->
            val player = sender as Player

            player.sendFormattedMessage(levelExperienceAmount, Component.text(ExperienceManager.getExperience(player), NamedTextColor.BLUE))
        }

        addSyntax(level, add, amount) { sender, args ->
            val player = sender as Player
            ExperienceManager.addExperience(player, ExperienceManager.experienceRequiredFor(args.get(amount)))
        }

        addSyntax(level, remove, amount) { sender, args ->
            val player = sender as Player
            ExperienceManager.removeExperience(player, ExperienceManager.experienceRequiredFor(args.get(amount)))
        }

        addSyntax(level, set, amount) { sender, args ->
            val player = sender as Player
            ExperienceManager.setExperience(player, ExperienceManager.experienceRequiredFor(args.get(amount) - 1))
        }

        addSyntax(reset, amount) { sender ->
            val player = sender as Player
            ExperienceManager.setExperience(player, 0)
        }


        addSyntax(xp, add, amount) { sender, args ->
            val player = sender as Player
            ExperienceManager.addExperience(player, args.get(amount))
        }

        addSyntax(xp, remove, amount) { sender, args ->
            val player = sender as Player
            ExperienceManager.removeExperience(player, args.get(amount))
        }

        addSyntax(xp, set, amount) { sender, args ->
            val player = sender as Player
            ExperienceManager.setExperience(player, args.get(amount))
        }


    }

}