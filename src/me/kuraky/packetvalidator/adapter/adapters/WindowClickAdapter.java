package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class WindowClickAdapter extends ValidatorAdapter {

    public WindowClickAdapter() {
        super(PacketType.Play.Client.WINDOW_CLICK);
    }

    @Override
    protected boolean isLegal(PacketContainer packet) {
        int window = packet.getIntegers().read(0);
        int slot = packet.getIntegers().read(1);
        int button = packet.getIntegers().read(2);
        String mode;

        try {
            Class<?> clazz = Class.forName(MinecraftReflection.getMinecraftPackage()+".InventoryClickType");
            mode = packet.getSpecificModifier(clazz).read(0).toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            ProtocolLibrary.getProtocolManager().removePacketListener(this);
            return true;
        }

        boolean legal = true;

        switch(mode) {
            case "PICKUP":
            case "CLONE":
            case "THROW":
            case "QUICK_MOVE":
                if(button < 0 || button > 7) legal = false;
                break;
            case "SWAP":
                if(button < 0 || button > 8) legal = false;
                break;
            case "QUICK_CRAFT":
                if(button < 0 || button > 10 || button == 3 || button == 7) legal = false;
                else if(button%2 == 0 && slot != -999) legal = false;
                else if(button%2 != 0 && slot == -999) legal = false;
                break;
            case "PICKUP_ALL":
                if(button != 0) legal = false;
                break;
        }

        if(legal && slot != -999) {
            if(slot < -1) legal = false;
            else if(slot > 36) {
                if(window == 0 && slot > 45) legal = false;
                else if (window > 0 && slot > 89) legal = false;
            }
        }
        return legal;
    }
}
