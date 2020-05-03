package me.kuraky.packetvalidator.adapter;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.kuraky.packetvalidator.adapter.adapters.*;

import java.util.ArrayList;

public class AdapterManager {

    public static ArrayList<ValidatorAdapter> adapters = new ArrayList<>();

    public static void init() {
        loadAdapters();
    }

    public static void registerAdapters() {
        for(ValidatorAdapter adapter : adapters) {
            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
            if(adapter.enabled) manager.addPacketListener(adapter);
            else manager.removePacketListener(adapter);
        }
    }

    private static void loadAdapters() {
        adapters.add(new TabCompleteAdapter());
        adapters.add(new ChatAdapter());
        adapters.add(new PickItemAdapter());
        adapters.add(new HeldItemSlotAdapter());
        adapters.add(new LookAdapter());
        adapters.add(new PositionLookAdapter());
        adapters.add(new WindowClickAdapter());
    }
}
