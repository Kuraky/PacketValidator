package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class TabCompleteAdapter extends ValidatorAdapter {

    public TabCompleteAdapter() {
        super(PacketType.Play.Client.TAB_COMPLETE);
    }

    @Override
    protected boolean isLegal(PacketContainer packet) {
        int length = packet.getStrings().read(0).length();
        return length <= 256 && length != 0;
    }
}
