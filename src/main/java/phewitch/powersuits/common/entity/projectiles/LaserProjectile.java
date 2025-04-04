package phewitch.powersuits.common.entity.projectiles;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class LaserProjectile extends AbstractArrow {
    public LaserProjectile(EntityType<LaserProjectile> entityType, Level world) {
        super(entityType, world);
        setNoGravity(true);
        pickup = Pickup.DISALLOWED;
        setBaseDamage(6);
    }

    public LaserProjectile(EntityType<LaserProjectile> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
        setNoGravity(true);
        pickup = Pickup.DISALLOWED;
        setBaseDamage(6);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);

        //this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1f, false, Level.ExplosionInteraction.NONE);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);

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

    @Override
    public void tick() {
        super.tick();

        if(this.tickCount > 200)
            this.remove(RemovalReason.DISCARDED);
    }
}
