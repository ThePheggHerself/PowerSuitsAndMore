package phewitch.powersuits.common.networking.packets.client2server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourChest;

import java.util.function.Supplier;

public class C2SSuitQuickLaunch {
    public C2SSuitQuickLaunch(){

    }
    public C2SSuitQuickLaunch(FriendlyByteBuf buf){
    }

    public void toBytes(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = context.getSender();
            var chest = SuitArmourChest.getChestplate(plr);

            if (chest != null) {
                chest.dischargeArmor(500, plr);
            }
        });

        return true;
    }
}
