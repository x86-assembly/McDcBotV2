package net.codemania;

import net.codemania.Events.Handler;
import org.bukkit.plugin.java.JavaPlugin;

public final class McDcBotV2 extends JavaPlugin {

    public static McDcBotV2 thisPlugin;

    @Override
    public void onEnable() {
        thisPlugin = this;

        getServer().getPluginManager().registerEvents(new Handler(), this);
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
