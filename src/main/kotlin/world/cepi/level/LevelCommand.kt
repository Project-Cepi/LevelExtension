package world.cepi.level

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.asSubcommand

class LevelCommand: Command("level") {

    init {

        val level = "level".asSubcommand()
        val xp = "xp".asSubcommand()

        val add = "add".asSubcommand()
        val set = "set".asSubcommand()
        val reset = "reset".asSubcommand()
        val remove = "remove".asSubcommand()

        val amount = ArgumentType.Integer("amount").min(0)

        addSyntax(level, add, amount) { sender, args ->
            val player = sender as Player
            ExperienceManager.addExperience(player, LevelDisplay.getMaxExperience(args.get(amount)))
        }

        addSyntax(level, remove, amount) { sender, args ->
            val player = sender as Player
            ExperienceManager.removeExperience(player, LevelDisplay.getMaxExperience(args.get(amount)))
        }

        addSyntax(level, set, amount) { sender, args ->
            val player = sender as Player
            ExperienceManager.setExperience(player, LevelDisplay.getMaxExperience(args.get(amount) - 1))
        }

        addSyntax(level, reset, amount) { sender ->
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

        addSyntax(xp, reset, amount) { sender ->
            val player = sender as Player
            ExperienceManager.setExperience(player, 0)
        }


    }

}