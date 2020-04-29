package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class PickItemAdapter extends ValidatorAdapter {

    public PickItemAdapter() {
        super(PacketType.Play.Client.PICK_ITEM);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        int slot = event.getPacket().getIntegers().read(0);
        if(slot < 9 || slot > 35) handleIllegalPacket(event);
    }
}
