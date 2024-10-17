package phewitch.powersuits.common.networking.packets.client2server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.CommonCore;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.function.Supplier;

public class C2SSuitShootFlames {
    public C2SSuitShootFlames() { }
    public C2SSuitShootFlames(FriendlyByteBuf buf) { }

    public void toBytes(FriendlyByteBuf buf) { }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = context.getSender();
            var lvl = plr.level();
            var inv = plr.getInventory();

            if(inv.getArmor(0).getItem() instanceof SuitArmourBase sAB) {
                var proj = new SmallFireball(lvl, plr, plr.getX(), plr.getY() + 1.5, plr.getZ());
                proj.setPos(plr.getX(), plr.getY() + 1.5, plr.getZ());
                proj.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0f, 1f * 3f, 1f);
                proj.tickCount = 50;
                proj.setNoGravity(true);

                if(sAB.features.abilities.contains(SuitFeatures.ABILITIES.SHOOT_FIRE_ARROWS))
                    proj.setSecondsOnFire(20);

                lvl.addFreshEntity(proj);
            }
        });

        return true;
    }
}
