package phewitch.powersuits.common.entity.mobs;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import phewitch.powersuits.common.CommonCore;
import phewitch.powersuits.common.entity.goals.OwnerAttackedGoal;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.Suits;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.UUID;

public class SuitSentry extends PathfinderMob implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    UUID ownerUUID;
    ArrayList<Integer> suitAbilities = new ArrayList<>();

    public SuitSentry(EntityType<? extends PathfinderMob> pEntityType, Level pLevel, Player owner, SuitArmourBase suit) {
        super(pEntityType, pLevel);
        ownerUUID = owner.getUUID();
    }


    public SuitSentry(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50D)
                .add(Attributes.ATTACK_DAMAGE, 6f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 1f).build();
    }

    public Player getOwner() {
        return getServer().getPlayerList().getPlayer(ownerUUID);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putUUID("suitowner", ownerUUID);
        pCompound.putIntArray("suitabilities", suitAbilities);

        super.addAdditionalSaveData(pCompound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        ownerUUID = pCompound.getUUID("suitowner");

        super.readAdditionalSaveData(pCompound);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.3f, true));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerAttackedGoal(this, this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false,
                (entity) -> entity instanceof Enemy && !(entity instanceof Creeper)));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",0, this::predicate));
        controllers.add(new AnimationController(this, "attackController",0, this::attackPredicate));
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (ownerUUID == null)
            return InteractionResult.FAIL;

        if (ownerUUID.equals(pPlayer.getUUID())) {
            if (CommonCore.hasAnyArmour(pPlayer)) {
                pPlayer.sendSystemMessage(Component.literal("You cannot be wearing armour"));
                return InteractionResult.FAIL;
            } else {
                pPlayer.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Suits.MK5_HELM.get()));
                pPlayer.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Suits.MK5_CHEST.get()));
                pPlayer.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Suits.MK5_LEGS.get()));
                pPlayer.setItemSlot(EquipmentSlot.FEET, new ItemStack(Suits.MK5_BOOTS.get()));

                this.remove(RemovalReason.DISCARDED);
                return InteractionResult.SUCCESS;
            }
        } else {
            pPlayer.hurt(pPlayer.damageSources().generic(), 10);
            pPlayer.sendSystemMessage(Component.literal("This suit does not belong to you!"));
            return InteractionResult.FAIL;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    private PlayState predicate(AnimationState aState) {
        if (aState.isMoving())
            aState.getController().setAnimation(RawAnimation.begin().then("animation.sentry.walk", Animation.LoopType.LOOP));
        else
            aState.getController().setAnimation(RawAnimation.begin().then("animation.sentry.idle", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationState aState) {
        if(this.swinging) {
            aState.getController().forceAnimationReset();
            aState.getController().setAnimation(RawAnimation.begin().then("animation.sentry.attack", Animation.LoopType.PLAY_ONCE));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }
}
