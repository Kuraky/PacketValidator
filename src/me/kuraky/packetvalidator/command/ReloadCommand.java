package me.kuraky.packetvalidator.command;

import me.kuraky.packetvalidator.config.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("packetvalidator.reload")) {
            ConfigManager.reload();
            commandSender.sendMessage("§aReloaded");
        }
        return true;
    }
}
