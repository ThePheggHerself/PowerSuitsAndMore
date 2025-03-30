package phewitch.powersuits.common.networking.packets.client2server;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourBase;

import java.util.function.Supplier;

public class S2CSuitDash {
    public S2CSuitDash(){

    }
    public S2CSuitDash(FriendlyByteBuf buf){
    }

    public void toBytes(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = Minecraft.getInstance().player;
            var sAB = SuitArmourBase.isWearingAnyPiece(plr);
            if(sAB != null)
                sAB.waterDash(plr.level(), plr);
        });

        return true;
    }
}
