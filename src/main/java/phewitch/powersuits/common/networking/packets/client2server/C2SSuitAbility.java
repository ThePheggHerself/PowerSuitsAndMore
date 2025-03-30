package phewitch.powersuits.common.networking.packets.client2server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.entity.EntityManager;
import phewitch.powersuits.common.entity.projectiles.ChestLaserProjectile;
import phewitch.powersuits.common.entity.projectiles.LaserProjectile;
import phewitch.powersuits.common.item.suits.armorbase.SuitAbilitiesManager;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourBase;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourChest;

import java.util.function.Supplier;

public class C2SSuitAbility {
    private int abilityCost;
    private int abilityType;

    public C2SSuitAbility(int cost, int type) {
        abilityCost = cost;
        abilityType = type;
    }

    public C2SSuitAbility(FriendlyByteBuf buf) {
        abilityCost = buf.readInt();
        abilityType = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(abilityCost);
        buf.writeInt(abilityType);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = context.getSender();

            if (SuitArmourBase.hasFullSet(plr)) {
                var lvl = plr.level();
                var inv = plr.getInventory();
                var target = plr.getForward();
                var sAB = SuitArmourChest.getChestplate(plr);
                var ability = ActiveAbilities.valueOf(abilityType);

                try {
                    sAB.dischargeArmor(abilityCost, plr);

                    System.out.println(sAB.getEnergy(plr));

                    switch (ability) {
                        default -> {
                            if (inv.contains(new ItemStack(Items.ARROW))) {
                                SuitAbilitiesManager.shootProjectile(lvl, plr, sAB, new Arrow(lvl, plr), 0, Items.ARROW);
                            } else {
                                plr.displayClientMessage(Component.literal("No Arrows Detected"), true);
                            }
                        }
                        case SHOOT_FIRE_ARROWS -> {
                            if (inv.contains(new ItemStack(Items.ARROW))) {
                                var arr = new Arrow(lvl, plr);
                                arr.setSecondsOnFire(30);
                                SuitAbilitiesManager.shootProjectile(lvl, plr, sAB, new Arrow(lvl, plr), 0, Items.ARROW);
                            }
                        }
                        case SENTRY_MODE -> {
                            SuitAbilitiesManager.sentryMode(lvl, plr, sAB);
                        }
                        case SHOOT_LASERS -> {
                            SuitAbilitiesManager.shootProjectile(lvl, plr, sAB, new LaserProjectile(EntityManager.LASER_PROJECTILE.get(), plr, lvl), 15);
                        }
                        case SHOOT_CHEST_LASER -> {
                            SuitAbilitiesManager.shootProjectile(lvl, plr, sAB, new ChestLaserProjectile(EntityManager.CHEST_LASER_PROJECTILE.get(), plr, lvl), 50);
                        }
                        case SHOOT_FLAMETHROWER -> {
                            SuitAbilitiesManager.shootProjectile(lvl, plr, sAB, new SmallFireball(lvl, plr, target.x, target.y, target.z), 10);
                        }
                        case SHOOT_ENDER_SHOT -> {
                            SuitAbilitiesManager.shootProjectile(lvl, plr, sAB, new DragonFireball(lvl, plr, target.x, target.y, target.z), 75);
                        }
                        case TELEPORT -> {
                            SuitAbilitiesManager.teleport(lvl, plr, sAB);
                        }
                        case SHOOT_WITHER_SKULLS -> {
                            SuitAbilitiesManager.shootProjectile(lvl, plr, sAB, new WitherSkull(lvl, plr, target.x, target.y, target.z), 40);
                        }
                        case WATER_DASH -> {
                            //if (sAB.features.activeA.contains(ActiveAbilities.WATER_DASH) && !plr.isAutoSpinAttack())
                            //    SuitAbilitiesManager.waterDash(lvl, plr);
                        }
                        case SONIC_BOOM -> {
                            SuitAbilitiesManager.sonicBoom(lvl, plr, sAB);

                        }
                    }


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        return true;
    }
}
