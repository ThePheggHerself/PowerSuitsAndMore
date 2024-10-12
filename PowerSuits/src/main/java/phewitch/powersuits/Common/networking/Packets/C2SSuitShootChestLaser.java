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
            if(context.getSender().getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB) {
                sAB.shootChestLaser(context.getSender().level(), context.getSender());
            }
        });

        return true;
    }
}
