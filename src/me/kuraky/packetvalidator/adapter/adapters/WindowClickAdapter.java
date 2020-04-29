package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.utility.MinecraftReflection;
import me.kuraky.packetvalidator.PacketValidator;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class WindowClickAdapter extends ValidatorAdapter {

    public WindowClickAdapter() {
        super(PacketType.Play.Client.WINDOW_CLICK);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        int window = packet.getIntegers().read(0);
        int slot = packet.getIntegers().read(1);
        int button = packet.getIntegers().read(2);
        String mode;

        try {
            Class<?> clazz = Class.forName(MinecraftReflection.getMinecraftPackage()+".InventoryClickType");
            mode = packet.getSpecificModifier(clazz).read(0).toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PacketValidator.getManager().removePacketListener(this);
            return;
        }

        boolean legal = true;

        switch(mode) { //validating buttons
            case "PICKUP":
            case "QUICK_MOVE":
            case "THROW":
                if(button != 0 && button != 1) legal = false;
                break;
            case "SWAP":
                if(button < 0 || button > 8) legal = false;
                break;
            case "CLONE":
                if(button != 2) legal = false;
                break;
            case "QUICK_CRAFT":
                if(button < 0 || button > 10 || button == 3 || button == 7) legal = false;
                else if(button%2 == 0 && slot != -999) legal = false;
                else if(button%2 != 0 && slot == -999) legal = false;
                break;
            case "PICKUP_ALL":
                if(button != 0) legal = false;
                break;
            default:
                legal = false;
                break;
        }

        if(legal && slot != -999) { //validating slots
            if(slot < -1) legal = false;
            else if(slot > 36) {
                if(window == 0 && slot > 45) legal = false;
                else if (window > 0 && slot > 89) legal = false;
            }
        }

        if(!legal) {
            System.out.println(slot + " " + button + " " + mode); //to remove
            handleIllegalPacket(event);
        }
    }
}
