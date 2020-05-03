package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class HeldItemSlotAdapter extends ValidatorAdapter {

    public HeldItemSlotAdapter() {
        super(PacketType.Play.Client.HELD_ITEM_SLOT);
    }

    @Override
    protected boolean isLegal(PacketContainer packet) {
        int slot = packet.getIntegers().read(0);
        return slot >= 0 && slot <= 8;
    }
}
