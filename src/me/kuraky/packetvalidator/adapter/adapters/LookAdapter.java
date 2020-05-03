package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class LookAdapter extends ValidatorAdapter {

    public LookAdapter() {
        super(PacketType.Play.Client.LOOK);
    }

    @Override
    protected boolean isLegal(PacketContainer packet) {
        float pitch = packet.getFloat().read(1);
        return !(Math.abs(pitch) > 90);
    }
}
