package net.codemania.discord.Commands;

import net.codemania.McDcBotV2;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Objects;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        switch (event.getName().toLowerCase()) {
            case "list":


                Collection<? extends Player> onlinePlayers = McDcBotV2.thisPlugin.getServer().getOnlinePlayers();
                int maxOnlinePlayers = McDcBotV2.thisPlugin.getServer().getMaxPlayers();

                StringBuilder messageBuilder = new StringBuilder();
                messageBuilder.append(String.format(":busts_in_silhouette: there are currently %d/%d players online:",
                        onlinePlayers.size(),
                        maxOnlinePlayers
                        ));

                for(Player player : onlinePlayers) {
                    messageBuilder.append("\n\t").append(player.getName());
                }
                String formattedMessage = messageBuilder.toString();

                event.reply(formattedMessage).queue();
                break;
            case "run":
                Bukkit.getScheduler().runTask(McDcBotV2.thisPlugin, () -> {
                    try {
                        McDcBotV2.thisPlugin.getServer().dispatchCommand(
                                McDcBotV2.thisPlugin.getServer().getConsoleSender(),
                                Objects.requireNonNull(event.getOption("command")).getAsString()
                        );
                        event.reply("Command executed successfully!").setEphemeral(true).queue();
                    } catch (Exception e) {
                        event.reply("Failed to execute command. Error: " + e.getMessage()).setEphemeral(true).queue();
                    }
                });
                break;
        }

    }

}
