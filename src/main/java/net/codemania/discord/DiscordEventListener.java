package net.codemania.discord;

import net.codemania.Chat.MessageSender;
import net.codemania.Service;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;

public class DiscordEventListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getAuthor().isBot()) return;

        if(!event.getChannel().getId().equals(Bot.publicChannelID)) return;

        Component displayName = Component.text(event.getAuthor().getEffectiveName());

        Component message = Component.text( event.getMessage().getContentRaw());

        MessageSender.sendChatMessage(displayName, message, Service.Discord());



    }

}
