package net.codemania.discord;

import net.codemania.discord.Commands.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {

    public static String privateChannelID;
    public static String publicChannelID;
    public static String guildID;
    public static Guild guild;
    public static String token;
    public static JDA discordBot;

    public static void initBot() {

        discordBot = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        discordBot.addEventListener(new DiscordEventListener());

        try {
            discordBot.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        guild = discordBot.getGuildById(guildID);

        CommandManager.registerCommands();

        discordBot.addEventListener(new CommandListener());

    }

}
