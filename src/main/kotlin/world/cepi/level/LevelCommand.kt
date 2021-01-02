package world.cepi.level

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.addSyntax
import world.cepi.kstom.arguments.ArgumentPlayer
import world.cepi.kstom.arguments.asSubcommand

class LevelCommand: Command("level") {

    init {

        val level = "level".asSubcommand()

        val add = "add".asSubcommand()
        val set = "set".asSubcommand()
        val reset = "reset".asSubcommand()
        val remove = "remove".asSubcommand()

        val amount = ArgumentType.Integer("amount")

        addSyntax(level, add, amount) { sender, args ->
            val player = sender as Player
            player.level += args.get(amount)
        }

        addSyntax(level, remove, amount) { sender, args ->
            val player = sender as Player
            player.level -= args.get(amount)
        }

        addSyntax(level, set, amount) { sender, args ->
            val player = sender as Player
            player.level = args.get(amount)
        }

        addSyntax(level, reset, amount) { sender ->
            val player = sender as Player
            player.level = 0
        }


    }

}