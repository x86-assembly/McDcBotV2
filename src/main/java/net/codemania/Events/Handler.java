package net.codemania.Events;


import io.papermc.paper.event.player.AsyncChatEvent;
import net.codemania.Chat.ComponentUtils;
import net.codemania.Chat.MessageGenerator;
import net.codemania.Chat.MessageSender;
import net.codemania.McDcBotV2;
import net.codemania.Service;
import net.codemania.Utils.PlayerDataHandler;
import net.codemania.discord.SendDiscordMessage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;


public class Handler implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();

        String rawMessage = LegacyComponentSerializer.legacyAmpersand().serialize(event.message());

        Component message = MiniMessage.miniMessage().deserialize(rawMessage);

        MessageSender.sendChatMessage(player, message, Service.Minecraft());



        SendDiscordMessage.sendMessage(String.format(
                ":speech_balloon: %s: %s",
                player.getName(),
                ComponentUtils.componentToPlaintext(message)
        ), false, true);


    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {



        SendDiscordMessage.sendMessage(
                ":crossed_swords: " + ComponentUtils.componentToPlaintext(event.deathMessage()),
                true,
                true
        );

        event.deathMessage(Objects.requireNonNull(event.deathMessage()).color(NamedTextColor.RED));


    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(
                MessageGenerator.generateJoinMessage(event.getPlayer())
        );

        SendDiscordMessage.sendMessage(
                String.format("%s has joined the game.\n\t There are now %d/%d online",
                        event.getPlayer().getName(),
                        McDcBotV2.thisPlugin.getServer().getOnlinePlayers().size(),
                        McDcBotV2.thisPlugin.getServer().getMaxPlayers()
                ),
                false,
                false);


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
         PlayerDataHandler.onPlayerDisconnect(event.getPlayer());


        event.quitMessage(
                MessageGenerator.generateLeaveMessage(event.getPlayer())
        );


        SendDiscordMessage.sendMessage(
                String.format("%s has left the game.\n\t There are now %d/%d online",
                        event.getPlayer().getName(),
                        McDcBotV2.thisPlugin.getServer().getOnlinePlayers().size(),
                        McDcBotV2.thisPlugin.getServer().getMaxPlayers()
                ),
                false,
                false);

    }


}
