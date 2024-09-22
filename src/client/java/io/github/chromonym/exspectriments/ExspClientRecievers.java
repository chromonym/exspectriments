package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.entities.PrinterBlockEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ExspClientRecievers {
    public static void registerReceivers() {
        ClientPlayNetworking.registerGlobalReceiver(ExspServerRecievers.PRINTER_PACKET_RECIEVER, (client, handler, buf, responseSender) -> {
            Exspectriments.LOGGER.info("sent");
            BlockPos pos = buf.readBlockPos();
            int cyan = buf.readInt();
            int magenta = buf.readInt();
            int yellow = buf.readInt();
            int black = buf.readInt();
            client.execute(() -> {
                BlockEntity patt11132$temp = client.world.getBlockEntity(pos);
                if (patt11132$temp instanceof PrinterBlockEntity printerBlockEntity) {
                    printerBlockEntity.updateColourFields(cyan, magenta, yellow, black);
                }
            });
         });
    }
}
