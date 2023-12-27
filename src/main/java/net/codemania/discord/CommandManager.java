package net.codemania.discord;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class CommandManager {

    public static void registerCommands() {

        Bot.guild.updateCommands().addCommands(
                Commands.slash("list", "Lists online players"),
                Commands.slash("run", "run a command as console sender on the server")
                        .addOptions(
                                new OptionData(OptionType.STRING, "command", "The command to run").setRequired(true)
                        )
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_SERVER))
        ).queue();

    }

}
