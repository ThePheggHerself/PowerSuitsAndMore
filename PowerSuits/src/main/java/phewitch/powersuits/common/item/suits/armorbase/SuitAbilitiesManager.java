package phewitch.powersuits.common.item.suits.armorbase;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import phewitch.powersuits.common.CommonCore;
import phewitch.powersuits.common.entity.OSSManager;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;
import phewitch.powersuits.common.sound.ModSounds;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class SuitAbilitiesManager {

    public static SuitArmourBase getSuitArmourBase(Player player){
        if(player.getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB){
            return sAB;
        }
        else return null;
    }

    public static void shootProjectile(Level lvl, ServerPlayer plr, SuitArmourBase sAB, Projectile proj, int powerDrain){
        shootProjectile(lvl, plr, sAB, proj, powerDrain, null, 0);
    }
    public static void shootProjectile(Level lvl, ServerPlayer plr, SuitArmourBase sAB, Projectile proj, int powerDrain, Item removalItem){
        shootProjectile(lvl, plr, sAB, proj, powerDrain, removalItem, 1);
    }
    public static void shootProjectile(Level lvl, ServerPlayer plr, SuitArmourBase sAB, Projectile proj, int powerDrain, Item removalItem, int removalCount) {
        try {
            if(lvl.isClientSide)
                return;

            proj.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0.0F, 1.5F, 1.0F);
            proj.tickCount = 50;
            lvl.addFreshEntity(proj);
            lvl.playSound(null, plr.getX(), plr.getY(), plr.getZ(), ModSounds.LASER_PEW.get(), SoundSource.PLAYERS, 3.0f, 1.0f);
            if(removalItem != null)
            {
                CommonCore.RemoveItemFromInventory(plr, removalItem, removalCount);
            }

        } catch (Exception e) {
            plr.server.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }
    public static void sentryMode(Level lvl, ServerPlayer plr, SuitArmourBase sAB) {
        if(lvl.isClientSide)
            return;

        try {
            OSSManager.SpawnSentry(plr, sAB.features.getModelName());
            plr.getInventory().armor.clear();
        } catch (Exception e) {
            plr.server.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }
    public static void teleport(Level lvl, ServerPlayer plr, SuitArmourBase sAB){
        if(lvl.isClientSide)
            return;

        var block = plr.pick(50, 1, false);
        if(block.getType() == HitResult.Type.BLOCK){
            var cost = new BigDecimal(block.getLocation().distanceTo(plr.position())).setScale(2, RoundingMode.UP).floatValue() * 3;
            plr.displayClientMessage(Component.literal("EEEE " + cost + " E "), false);

            if(sAB.features.hasPower(cost))
            {
                plr.teleportTo(block.getLocation().x, block.getLocation().y, block.getLocation().z);
            }
        }



    }
    public static void sonicBoom(Level lvl, ServerPlayer plr, SuitArmourBase sAB){
        if(lvl.isClientSide)
            return;

        lvl.playSound(null, plr.getX(), plr.getY(), plr.getZ(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 3.0f, 1.0f);

        Vec3 target = plr.position().add(plr.getLookAngle().scale(20));
        Vec3 source = plr.position().add(0, 1, 0);
        Vec3 offset = target.subtract(source);
        Vec3 normalized = offset.normalize();

        Set<Entity> hit = new HashSet<>();
        for (int particleIndex = 1; particleIndex < Mth.floor(offset.length()) + 7; particleIndex++) {
            Vec3 particlePos = source.add(normalized.scale(particleIndex));
            ((ServerLevel) lvl).sendParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1, 0.0, 0.0, 0.0, 0.0);

            hit.addAll(lvl.getEntitiesOfClass(LivingEntity.class, new AABB(new BlockPos((int) particlePos.x, (int) particlePos.y, (int) particlePos.z)).inflate(2)));
            hit.remove(plr);

            for (Entity hitTarget : hit) {
                if (hitTarget instanceof LivingEntity living) {
                    living.hurt(plr.damageSources().sonicBoom(plr), 30);
                }
            }
        }
    }
}
