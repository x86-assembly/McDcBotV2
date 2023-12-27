package net.codemania;

import net.codemania.Chat.MessageGenerator;
import net.codemania.Events.Handler;
import net.codemania.Utils.PlayerDataHandler;
import net.codemania.discord.Bot;
import net.codemania.discord.SendDiscordMessage;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;


public final class McDcBotV2 extends JavaPlugin {

    public static McDcBotV2 thisPlugin;


    @Override
    public void onEnable() {
        thisPlugin = this;

        saveResource("config.yml", false);
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new Handler(), this);

        Bot.privateChannelID = getConfig().getString("discord.admin_channel_id");
        Bot.publicChannelID = getConfig().getString("discord.public_channel_id");
        Bot.token = getConfig().getString("discord.bot_secret");
        Bot.guildID = getConfig().getString("discord.guild_id");
        Bot.initBot();

        List<List<String>> joinMessages = (List<List<String>>) getConfig().getList("join_messages");
        if (joinMessages == null || joinMessages.isEmpty()) {
            getLogger().warning("Unable to parse join messages");
        }
        MessageGenerator.joinMessages = joinMessages;

        List<List<String>> leaveMessages = (List<List<String>>) getConfig().getList("leave_messages");
        if (joinMessages == null || joinMessages.isEmpty()) {
            getLogger().warning("Unable to parse leave messages");
        }
        MessageGenerator.leaveMessages = leaveMessages;


        SendDiscordMessage.sendMessage("Starting plugin", true, false);


        PlayerDataHandler.initialize(this);
    }


    @Override
    public void onDisable() {

        SendDiscordMessage.sendMessage("Stopping plugin", true, false);

    }

}
