package io.github.chromonym.exspectriments;

import io.github.chromonym.exspectriments.entities.PrinterBlockEntity;
import io.github.chromonym.exspectriments.screenhandlers.PrinterScreenHandler;
import java.util.Iterator;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class ExspServerRecievers {
    public static final Identifier PRINTER_PACKET_RECIEVER = new Identifier(Exspectriments.MOD_ID, "printer");

    public static void registerReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(PRINTER_PACKET_RECIEVER, (server, player, handler, buf, responseSender) -> {
            ScreenHandler patt2109$temp = player.currentScreenHandler;
            if (patt2109$temp instanceof PrinterScreenHandler printerScreenHandler) {
               PrinterBlockEntity blockEntity = printerScreenHandler.getBlockEntity();
               if (blockEntity != null) {
                  int cyan = buf.readInt();
                  int magenta = buf.readInt();
                  int yellow = buf.readInt();
                  int black = buf.readInt();
                  blockEntity.updateColourFields(cyan, magenta, yellow, black);
                  PacketByteBuf outgoingBuf = PacketByteBufs.create();
                  outgoingBuf.writeBlockPos(blockEntity.getPos());
                  outgoingBuf.writeInt(cyan);
                  outgoingBuf.writeInt(magenta);
                  outgoingBuf.writeInt(yellow);
                  outgoingBuf.writeInt(black);
                  Iterator var9 = PlayerLookup.tracking((ServerWorld)blockEntity.getWorld(), blockEntity.getPos()).iterator();
   
                  while(var9.hasNext()) {
                     ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)var9.next();
                     ServerPlayNetworking.send(serverPlayerEntity, PRINTER_PACKET_RECIEVER, outgoingBuf);
                  }
               }
            }
   
         });
    }
}
