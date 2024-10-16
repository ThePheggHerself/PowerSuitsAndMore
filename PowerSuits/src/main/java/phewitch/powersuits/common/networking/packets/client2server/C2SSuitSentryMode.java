package phewitch.powersuits.common.networking.packets.client2server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.CommonCore;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;

import java.util.function.Supplier;

public class C2SSuitSentryMode {
    public C2SSuitSentryMode(){ }
    public C2SSuitSentryMode(FriendlyByteBuf buf){ }

    public void toBytes(FriendlyByteBuf buf){ }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = context.getSender();
            var lvl = plr.level();
            var inv = plr.getInventory();

            if(inv.getArmor(0).getItem() instanceof SuitArmourBase sAB) {
                sAB.sentryMode(lvl, plr);
            }
        });

        return true;
    }
}
