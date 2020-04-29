# PacketValidator
Spigot plugin for validating the correctness of some data sent by the client

Currently validates following packets:
- Tab Complete
- Chat
- Pick Item
- Held Item Slot
- Look
- Position Look
- Window Click (that one might be a bit buggy due to how big the packet is)

Sending illegal data with any of these packets will cancel the packet and result in a kick

Note the plugin is very "raw", there is absolutely no configuration available. 
The plugin also offers no information for the server staff.

## Dependency:
- ProtocolLib
