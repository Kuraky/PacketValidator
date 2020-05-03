package me.kuraky.packetvalidator.config;

import me.kuraky.packetvalidator.PacketValidator;
import me.kuraky.packetvalidator.adapter.AdapterManager;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConfigManager {

    private static FileConfiguration config;

    private static String playerMessage;
    private static String adminMessage;

    public static void init() {
        PacketValidator plugin = PacketValidator.getINSTANCE();
        plugin.saveDefaultConfig();
        config = plugin.getConfig();

        loadValues();
    }

    private static void loadValues() {
        playerMessage = getString("player-message").replace('&', '§');
        adminMessage = getString("admin-message").replace('&', '§');

        loadAdapterValues();
    }

    private static void loadAdapterValues() {
        for(ValidatorAdapter adapter : AdapterManager.adapters) {
            String path = convertNameToPath(adapter.getType());
            adapter.enabled = getBoolean(path + "enabled");
            adapter.block = getBoolean(path + "block");
            adapter.setAction(getString(path + "action"));
        }

        AdapterManager.registerAdapters();
    }

    public static void reload() {
        PacketValidator plugin = PacketValidator.getINSTANCE();
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();

        loadValues();
    }

    private static String convertNameToPath(String path) {
        return path.toLowerCase().replace("_", "-") + ".";
    }

    public static String getPlayerMessage() {
        return playerMessage;
    }

    public static String getAdminMessage(Player player, String packetName) {
        return adminMessage.replace("%player%", player.getName()).replace("%packet%", packetName);
    }

    private static String getString(String path) {
        return config.getString(path);
    }

    private static boolean getBoolean(String path) {
        return config.getBoolean(path);
    }
}
