package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class HeldItemSlotAdapter extends ValidatorAdapter {

    public HeldItemSlotAdapter() {
        super(PacketType.Play.Client.HELD_ITEM_SLOT);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        int slot = event.getPacket().getIntegers().read(0);
        if(slot < 0 || slot > 8) handleIllegalPacket(event);
    }
}
