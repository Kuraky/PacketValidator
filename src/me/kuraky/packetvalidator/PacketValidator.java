package me.kuraky.packetvalidator;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.kuraky.packetvalidator.adapter.adapters.*;
import org.bukkit.plugin.java.JavaPlugin;

public class PacketValidator extends JavaPlugin {

    private static PacketValidator INSTANCE;
    private static ProtocolManager manager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        manager = ProtocolLibrary.getProtocolManager();

        loadAdapters();
    }

    private void loadAdapters() {
        manager.addPacketListener(new TabCompleteAdapter());
        manager.addPacketListener(new ChatAdapter());
        manager.addPacketListener(new PickItemAdapter());
        manager.addPacketListener(new HeldItemSlotAdapter());
        manager.addPacketListener(new LookAdapter());
        manager.addPacketListener(new PositionLookAdapter());
        manager.addPacketListener(new WindowClickAdapter());
    }

    public static PacketValidator getINSTANCE() {
        return INSTANCE;
    }

    public static ProtocolManager getManager() {
        return  manager;
    }
}
