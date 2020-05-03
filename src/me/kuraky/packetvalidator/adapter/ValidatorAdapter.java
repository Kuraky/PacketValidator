package me.kuraky.packetvalidator.adapter;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.kuraky.packetvalidator.PacketValidator;
import me.kuraky.packetvalidator.config.ConfigManager;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ValidatorAdapter extends PacketAdapter {

    public boolean enabled;
    public boolean block;
    private AdapterAction action;
    private String type;

    public ValidatorAdapter(PacketType type) {
        super(PacketValidator.getINSTANCE(), ListenerPriority.HIGHEST, type);
        this.enabled = true;
        this.block = true;
        this.action = AdapterAction.KICK;
        this.type = type.name();
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        if(enabled) {
            if (!isLegal(event.getPacket())) handleIllegalPacket(event);
        }
    }

    protected boolean isLegal(PacketContainer packet) {
        return true;
    }

    private void handleIllegalPacket(PacketEvent event) {
        if(block) event.setCancelled(true);
        if(action != AdapterAction.NONE) {
            Bukkit.getScheduler().runTask(PacketValidator.getINSTANCE(), () -> {
                Player player = event.getPlayer();
                if (player != null && player.isOnline()) {

                    String playerMessage = ConfigManager.getPlayerMessage();
                    if(action == AdapterAction.BAN) Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), playerMessage, null, null);
                    player.kickPlayer(playerMessage);

                    String adminMessage = ConfigManager.getAdminMessage(player, type);
                    for(Player loopedPlayer : Bukkit.getOnlinePlayers()) {
                        if(loopedPlayer.hasPermission("packetvalidator.notify")) {
                            loopedPlayer.sendMessage(adminMessage);
                        }
                    }
                }
            });
        }
    }

    public void setAction(String action) {
        AdapterAction newAction = AdapterAction.KICK;
        if(action.equalsIgnoreCase("none")) newAction = AdapterAction.NONE;
        else if(action.equalsIgnoreCase("ban")) newAction = AdapterAction.BAN;
        this.action = newAction;
    }

    public String getType() {
        return this.type;
    }
}
