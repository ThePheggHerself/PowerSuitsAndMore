package phewitch.powersuits.Common.networking.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.Common.Entity.EntityManager;
import phewitch.powersuits.Common.Entity.Projectiles.ChestLaserProjectile;
import phewitch.powersuits.Common.Entity.Projectiles.LaserProjectile;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;

import java.util.function.Supplier;

public class C2SSuitShootChestLaser {
    public C2SSuitShootChestLaser(){ }
    public C2SSuitShootChestLaser(FriendlyByteBuf buf){ }

    public void toBytes(FriendlyByteBuf buf){ }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = context.getSender();
            var lvl = plr.level();
            var inv = plr.getInventory();



            if(inv.getArmor(0).getItem() instanceof SuitArmourBase sAB) {
                try {
                    var proj = new ChestLaserProjectile(EntityManager.CHEST_LASER_PROJECTILE.get(), plr, lvl);
                    proj.setPos(plr.getX(), plr.getY() + 1.5, plr.getZ());
                    proj.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0f, 5f, 1f);
                    proj.tickCount = 50;
                    lvl.addFreshEntity(proj);
                }
                catch (Exception e){
                    plr.server.sendSystemMessage(Component.literal(e.getMessage()));
                }
            }
        });

        return true;
    }
}
