package me.kuraky.packetvalidator.adapter;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.kuraky.packetvalidator.PacketValidator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ValidatorAdapter extends PacketAdapter {

    private static PacketValidator plugin;

    public ValidatorAdapter(PacketType type) {
        super(PacketValidator.getINSTANCE(), ListenerPriority.HIGHEST, type);
        plugin = PacketValidator.getINSTANCE();
    }

    protected static void handleIllegalPacket(PacketEvent event) {
        event.setCancelled(true);
        Bukkit.getScheduler().runTask(plugin, () -> {
            Player player = event.getPlayer();
            if(player != null && player.isOnline()) player.kickPlayer("§cILLEGAL PACKET");
        });
    }
}
