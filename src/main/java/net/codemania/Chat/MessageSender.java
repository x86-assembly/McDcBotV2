package net.codemania.Chat;

import net.codemania.McDcBotV2;
import net.codemania.Service;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;



public class MessageSender {

    public static void sendChatMessage(Player player, Component message, Service service) {
        Component finalMessage = MessageGenerator.generateChatMessage(player, message, Service.Minecraft());

        sendMessageToAllOnlinePlayers(finalMessage);

    }

    public static void sendChatMessage(Component player, Component message, Service service) {

        sendMessageToAllOnlinePlayers(
                Component.empty()
                        .append(MessageGenerator.generateServiceBadge(service))
                        .append(player.color(NamedTextColor.YELLOW))
                        .append(Component.text(": ", NamedTextColor.GRAY))
                        .append(message)
        );

    }

    private static void sendMessageToAllOnlinePlayers(Component content) {
        Server server = McDcBotV2.thisPlugin.getServer();

        for(Player currentPlayer : server.getOnlinePlayers()) {

            currentPlayer.sendMessage(content);

        }

        server.getConsoleSender().sendMessage(content);

    }


}
