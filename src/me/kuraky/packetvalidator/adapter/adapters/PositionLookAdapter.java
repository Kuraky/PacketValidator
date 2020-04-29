package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class PositionLookAdapter extends ValidatorAdapter {

    public PositionLookAdapter() {
        super(PacketType.Play.Client.POSITION_LOOK);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        float pitch = event.getPacket().getFloat().read(1);
        if(Math.abs(pitch) > 90) {
            System.out.println(pitch); //to remove
            handleIllegalPacket(event);
        }
    }
}