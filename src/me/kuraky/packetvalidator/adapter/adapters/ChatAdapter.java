package me.kuraky.packetvalidator.adapter.adapters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;
import me.kuraky.packetvalidator.adapter.ValidatorAdapter;

public class ChatAdapter extends ValidatorAdapter {

    public ChatAdapter() {
        super(PacketType.Play.Client.CHAT);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        int length = event.getPacket().getStrings().read(0).length();
        if(length > 256 || length == 0) handleIllegalPacket(event);
    }
}
