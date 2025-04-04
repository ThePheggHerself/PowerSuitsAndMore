package phewitch.powersuits.common.entity.projectiles;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class ChestLaserProjectile extends AbstractArrow {
    public ChestLaserProjectile(EntityType<ChestLaserProjectile> entityType, Level world) {
        super(entityType, world);
        setNoGravity(true);
        pickup = Pickup.DISALLOWED;
        setBaseDamage(20);
    }

    public ChestLaserProjectile(EntityType<ChestLaserProjectile> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
        setNoGravity(true);
        pickup = Pickup.DISALLOWED;
        setBaseDamage(20);

        Vec3 vec3 = shooter.getViewVector(1.0F);
        double d2 = (shooter.getX() + vec3.x * 1.0D);
        double d3 = (0.5D + shooter.getY(0.5D));
        double d4 = (shooter.getZ() + vec3.z * 1.0D);
        setPos(d2, d3, d4);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);

        entityHitResult.getEntity().setSecondsOnFire(10);
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3f, false, Level.ExplosionInteraction.NONE);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3f, true, Level.ExplosionInteraction.TNT);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.tickCount > 200)
            this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        try {
            return NetworkHooks.getEntitySpawningPacket(this);
        }
        catch (Exception e){
            this.getServer().sendSystemMessage(Component.literal(e.getMessage()));
            return NetworkHooks.getEntitySpawningPacket(this);
        }
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundType.EMPTY.getHitSound();
    }
}
