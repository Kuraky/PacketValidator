# PacketValidator
Spigot plugin for validating the correctness of some data sent by the client

Currently validates following packets:
- Tab Complete
- Chat
- Pick Item
- Held Item Slot
- Look
- Position Look
- Window Click

Sending illegal data with any of these packets will cancel the packet and result in a punishment, specified in the config

## Dependency:
- ProtocolLib
