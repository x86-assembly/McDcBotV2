package net.codemania.Config;

import net.codemania.McDcBotV2;

public class ConfigManager {


    private static McDcBotV2 plugin;

    public static void initConfig() {
        plugin = McDcBotV2.thisPlugin;

        plugin.saveResource("config.yml", false);

        plugin.saveDefaultConfig();


    }



}
