package phewitch.powersuits.common.networking.packets.client2server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.CommonCore;
import phewitch.powersuits.common.entity.EntityManager;
import phewitch.powersuits.common.entity.projectiles.ChestLaserProjectile;
import phewitch.powersuits.common.entity.projectiles.LaserProjectile;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;

import java.util.function.Supplier;

public class C2SSuitAbility {
    private int abilityKey;
    private int abilityType;

    public C2SSuitAbility(int key, int type) {
        abilityKey = key;
        abilityType = type;
    }
    public C2SSuitAbility(FriendlyByteBuf buf) {
        abilityKey = buf.readInt();
        abilityType = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(abilityKey);
        buf.writeInt(abilityType);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = context.getSender();
            var lvl = plr.level();
            var inv = plr.getInventory();

            if(inv.getArmor(0).getItem() instanceof SuitArmourBase sAB){
                if(sAB.features.activeA.get(abilityKey).getValue() == abilityType){
                    switch (sAB.features.activeA.get(abilityKey)){
                        default -> {
                            if(inv.contains(new ItemStack(Items.ARROW))) {
                                CommonCore.RemoveItemFromInventory(plr, Items.ARROW, 1);
                                sAB.shootProjectile(lvl, plr, new Arrow(lvl, plr), 0);
                            }
                            else {
                                plr.displayClientMessage(Component.literal("No Arrows Detected"), true);
                            }
                        }
                        case SHOOT_FIRE_ARROWS -> {
                            if(inv.contains(new ItemStack(Items.ARROW))) {
                                CommonCore.RemoveItemFromInventory(plr, Items.ARROW, 1);
                                var arr = new Arrow(lvl, plr);
                                arr.setSecondsOnFire(30);
                                sAB.shootProjectile(lvl, plr, new Arrow(lvl, plr), 0);
                            }
                        }
                        case SENTRY_MODE -> {
                            sAB.sentryMode(lvl, plr);
                        }
                        case SHOOT_LASERS -> {
                            sAB.shootProjectile(lvl, plr, new LaserProjectile(EntityManager.LASER_PROJECTILE.get(), plr, lvl), 15);
                        }
                        case SHOOT_CHEST_LASER -> {
                            sAB.shootProjectile(lvl, plr, new ChestLaserProjectile(EntityManager.CHEST_LASER_PROJECTILE.get(), plr, lvl), 50);
                        }
                        case SHOOT_FLAMETHROWER -> {
                            sAB.shootProjectile(lvl, plr, new SmallFireball(lvl, plr, plr.getX(), plr.getY() + 1.5, plr.getZ()), 15);
                        }
                        case SHOOT_ENDER_SHOT -> {
                            //TODO
                        }
                        case TELEPORT -> {
                            sAB.teleport(lvl, plr);
                        }
                        case SHOOT_WITHER_SKULLS -> {
                            //TODO
                        }
                        case SONIC_BOOM -> {
                            //TODO
                        }
                        case WATER_DASH -> {
                            //TODO
                        }
                    }
                }
            }
        });

        return true;
    }
}
