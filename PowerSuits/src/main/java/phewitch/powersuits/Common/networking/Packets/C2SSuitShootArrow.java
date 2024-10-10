package phewitch.powersuits.Common.networking.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.Common.CommonCore;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;

import java.util.function.Supplier;

public class C2SSuitShootArrow {
    public C2SSuitShootArrow(){

    }

    public C2SSuitShootArrow(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = context.getSender();
            var lvl = plr.level();
            var inv = plr.getInventory();
            var arrowStack = new ItemStack(Items.ARROW);

            if(inv.getArmor(0).getItem() instanceof SuitArmourBase sAB && inv.contains(arrowStack)) {
                CommonCore.RemoveItemFromInventory(plr, Items.ARROW, 1);
                var arrow = new Arrow(lvl, plr);
                arrow.setBaseDamage(sAB.projectileDamage);
                arrow.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0f, 1f * 3f, 1f);
                arrow.tickCount = 50;
                lvl.addFreshEntity(arrow);
            }
        });

        return true;
    }
}
