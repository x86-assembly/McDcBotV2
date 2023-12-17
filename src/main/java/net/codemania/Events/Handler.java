package net.codemania.Events;


import io.papermc.paper.event.player.AsyncChatEvent;
import net.codemania.Chat.MessageGenerator;
import net.codemania.McDcBotV2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class Handler implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        Component message = event.message();
        TextColor minecraftColor = TextColor.color(Color.GREEN.asRGB());

        Component finalMessage = MessageGenerator.generateChatMessage(
                player,
                message,
                "M",
                "This message was sent from a player in Minecraft",
                minecraftColor,
                minecraftColor
        );


        for(Player reciever: McDcBotV2.thisPlugin.getServer().getOnlinePlayers()) {
            reciever.sendMessage(finalMessage);
        }

    }

}
