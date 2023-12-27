package net.codemania.discord;

import net.codemania.McDcBotV2;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class SendDiscordMessage {

    public static void sendMessage(String msg, boolean silent, boolean isPublic) {

        TextChannel textChannel = Bot.discordBot.getTextChannelById(isPublic ? Bot.publicChannelID : Bot.privateChannelID);

        if (textChannel == null) {
            McDcBotV2.thisPlugin.getLogger().warning("Invalid public channel id in McDcBotV2/config.yml !");
        }

        textChannel.sendMessage(msg).setSuppressedNotifications(silent).queue();

    }

}
