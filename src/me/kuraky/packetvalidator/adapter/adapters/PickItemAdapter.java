package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class PickItemAdapter extends ValidatorAdapter {

    public PickItemAdapter() {
        super(PacketType.Play.Client.PICK_ITEM);
    }

    @Override
    protected boolean isLegal(PacketContainer packet) {
        int slot = packet.getIntegers().read(0);
        return slot >= 9 && slot <= 35;
    }
}
