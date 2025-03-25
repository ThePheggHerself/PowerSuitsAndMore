package phewitch.powersuits.common.networking.packets.client2server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.entity.EntityManager;
import phewitch.powersuits.common.entity.projectiles.ChestLaserProjectile;
import phewitch.powersuits.common.entity.projectiles.LaserProjectile;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;

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

            System.out.println("SUIT ABILITY TRIGGERED");

            if(inv.getArmor(0).getItem() instanceof SuitArmourBase sAB){
                if(sAB.features.activeA.get(abilityKey).getValue() == abilityType){
                    var target = plr.getForward();

                    System.out.println(sAB.features.activeA.get(abilityKey));

                    try {
                        switch (sAB.features.activeA.get(abilityKey)) {
                            default -> {
                                if (inv.contains(new ItemStack(Items.ARROW))) {
                                    sAB.shootProjectile(lvl, plr, new Arrow(lvl, plr), 0, Items.ARROW);
                                } else {
                                    plr.displayClientMessage(Component.literal("No Arrows Detected"), true);
                                }
                            }
                            case SHOOT_FIRE_ARROWS -> {
                                if (inv.contains(new ItemStack(Items.ARROW))) {
                                    var arr = new Arrow(lvl, plr);
                                    arr.setSecondsOnFire(30);
                                    sAB.shootProjectile(lvl, plr, new Arrow(lvl, plr), 0, Items.ARROW);
                                }
                            }
                            case SENTRY_MODE -> {
                                sAB.sentryMode(lvl, plr);
                            }
                            case SHOOT_LASERS -> {
                                if (sAB.features.hasPower(15))
                                    sAB.shootProjectile(lvl, plr, new LaserProjectile(EntityManager.LASER_PROJECTILE.get(), plr, lvl), 15);
                            }
                            case SHOOT_CHEST_LASER -> {
                                if (sAB.features.hasPower(50))
                                    sAB.shootProjectile(lvl, plr, new ChestLaserProjectile(EntityManager.CHEST_LASER_PROJECTILE.get(), plr, lvl), 50);
                            }
                            case SHOOT_FLAMETHROWER -> {
                                if (sAB.features.hasPower(10))
                                    sAB.shootProjectile(lvl, plr, new SmallFireball(lvl, plr, target.x, target.y, target.z), 10);
                            }
                            case SHOOT_ENDER_SHOT -> {
                                if (sAB.features.hasPower(75))
                                    sAB.shootProjectile(lvl, plr, new DragonFireball(lvl, plr, target.x, target.y, target.z), 75);
                            }
                            case TELEPORT -> {
                                sAB.teleport(lvl, plr);
                            }
                            case SHOOT_WITHER_SKULLS -> {
                                if (sAB.features.hasPower(40))
                                    sAB.shootProjectile(lvl, plr, new WitherSkull(lvl, plr, target.x, target.y, target.z), 40);
                            }
                            case WATER_DASH -> {
                                if (sAB.features.hasPower(30) && sAB.features.activeA.contains(ActiveAbilities.WATER_DASH) && !plr.isAutoSpinAttack())
                                    sAB.waterDash(lvl, plr);
                            }
                            case SONIC_BOOM -> {
                                if (sAB.features.hasPower(150)) {
                                    sAB.sonicBoom(lvl, plr);
                                }
                            }
                        }
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        });

        return true;
    }
}
