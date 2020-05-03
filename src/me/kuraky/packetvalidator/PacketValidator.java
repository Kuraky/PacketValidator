package me.kuraky.packetvalidator;

import me.kuraky.packetvalidator.command.ReloadCommand;
import me.kuraky.packetvalidator.adapter.AdapterManager;
import me.kuraky.packetvalidator.config.ConfigManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class PacketValidator extends JavaPlugin {

    private static PacketValidator INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        AdapterManager.init();
        ConfigManager.init(); //should be second to properly register adapters

        PluginCommand command = getCommand("pvreload");
        if(command != null) command.setExecutor(new ReloadCommand());
    }

    public static PacketValidator getINSTANCE() {
        return INSTANCE;
    }
}
