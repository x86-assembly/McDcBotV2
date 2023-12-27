package net.codemania.Utils;

import net.codemania.McDcBotV2;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PlayerDataHandler {

    private static McDcBotV2 plugin;

    public static void initialize(McDcBotV2 instance) {
        plugin = instance;
    }

    public static void savePlayerData(Player player) {
        UUID uuid = player.getUniqueId();
        File dataFolder = new File(plugin.getDataFolder() + File.separator + "playerdata");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File playerFile = new File(dataFolder, uuid.toString() + ".yml");

        try {
            FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile);

            // Save relevant player data
            config.set("inventory", player.getInventory().getContents());
            config.set("enderchest", player.getEnderChest().getContents());
            config.set("health", player.getHealth());
            config.set("hunger", player.getFoodLevel());
            config.set("gamemode", player.getGameMode().toString());
            config.set("location", player.getLocation().serialize());
            config.set("dimension", player.getWorld().getEnvironment().name());

            // Save active potion effects
            List<Map<String, Object>> potionEffects = new ArrayList<>();
            for (PotionEffect effect : player.getActivePotionEffects()) {
                Map<String, Object> effectData = new HashMap<>();
                effectData.put("type", effect.getType().getName());
                effectData.put("duration", effect.getDuration());
                potionEffects.add(effectData);
            }
            config.set("potioneffects", potionEffects);

            // Save to file asynchronously
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try {
                    config.save(playerFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> loadPlayerData(UUID uuid) {
        File playerFile = new File(plugin.getDataFolder() + File.separator + "playerdata", uuid.toString() + ".yml");
        if (!playerFile.exists()) {
            return null;
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
        return config.getValues(true);
    }

    public static void onPlayerDisconnect(Player player) {
        if (player == null) {
            return;
        }

        savePlayerData(player);
    }

    // Call this function with a player's name to retrieve stored data
    public static Map<String, Object> getPlayerData(String playerName) {
        Player player = Bukkit.getPlayerExact(playerName);
        if (player != null) {
            // Player is online, retrieve data directly
            return loadPlayerData(player.getUniqueId());
        } else {
            // Player is offline, load data from file
            UUID uuid = Bukkit.getOfflinePlayer(playerName).getUniqueId();
            return loadPlayerData(uuid);
        }
    }
}
