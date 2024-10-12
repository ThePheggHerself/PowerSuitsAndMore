package phewitch.powersuits.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;

import java.util.function.Supplier;

public class C2SSuitShootLaser {
    public C2SSuitShootLaser(){ }
    public C2SSuitShootLaser(FriendlyByteBuf buf){ }

    public void toBytes(FriendlyByteBuf buf){ }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            if(context.getSender().getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB) {
                sAB.shootLaser(context.getSender().level(), context.getSender());
            }
        });

        return true;
    }
}
