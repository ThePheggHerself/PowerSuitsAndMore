package phewitch.powersuits.common.item.suits.armorbase;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.NotNull;
import phewitch.powersuits.client.KeyBinding;
import phewitch.powersuits.client.gui.GUIManager;
import phewitch.powersuits.client.gui.IHUDItem;
import phewitch.powersuits.common.CommonCore;
import phewitch.powersuits.common.entity.OSSManager;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.client2server.C2SSuitAbility;
import phewitch.powersuits.common.networking.packets.server2client.S2CSuitPowerSync;
import phewitch.powersuits.common.sound.ModSounds;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class SuitArmourBase extends ArmorItem implements GeoItem, IHUDItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public SuitFeatures features;

    public SuitArmourBase(ArmorMaterial materialIn, Type type, Properties properties, SuitTemplate template) {
        super(materialIn, type, properties);
        this.features = new SuitFeatures(template);

        GUIManager.registerHUDItem(features.getModelName() + "_armor", this);
    }

    public static SuitArmourBase isWearingAnyPiece(Player plr){
        for (var i : plr.getInventory().armor){
            if(i.getItem() instanceof SuitArmourBase sAB)
                return sAB;
        }

        return null;
    }

    public Boolean hasBoots(Player player) {
        if (player == null)
            return false;

        return (player.getInventory().getArmor(0).getItem() instanceof ArmorItem ai) && ai.getMaterial() == material;
    }
    public Boolean hasLegs(Player player) {
        if (player == null)
            return false;

        return (player.getInventory().getArmor(1).getItem() instanceof ArmorItem ai) && ai.getMaterial() == material;
    }
    public Boolean hasChestplate(Player player) {
        if (player == null)
            return false;

        return (player.getInventory().getArmor(2).getItem() instanceof ArmorItem ai) && ai.getMaterial() == material;
    }
    public Boolean hasHelmet(Player player) {
        if (player == null)
            return false;

        return (player.getInventory().getArmor(3).getItem() instanceof ArmorItem ai) && ai.getMaterial() == material;
    }
    public Boolean hasFullSet(Player player) {
        if (player == null)
            return false;

        return hasBoots(player) && hasLegs(player) && hasChestplate(player) && hasHelmet(player);
    }
    public boolean hasBootsOrChestplate(Player player) {
        return hasBoots(player) || hasChestplate(player);
    }
    public Boolean isWeak(LivingHurtEvent ev){
        var type = ev.getSource();

        if(features.weaknesses.contains(Weakness.FIRE) &&
                (type.is(DamageTypes.IN_FIRE) || type.is(DamageTypes.ON_FIRE) || type.is(DamageTypes.FIREBALL) || type.is(DamageTypes.LAVA)))
            return true;

        if(features.weaknesses.contains(Weakness.WATER) &&
                (ev.getEntity().isInWater() || type.is(DamageTypes.DROWN)))
            return true;

        return false;
    }
    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    public void armourTick(TickEvent.PlayerTickEvent ev) {
        if(ev.player.level().isClientSide){
            if (hasFullSet(ev.player)) {
                doLimitedFlight(ev);
                doFullFlight(ev);
                tryChargeArmour(ev);

                if(features.projCooldown > 0)
                    features.projCooldown -= 1;
            } else {
                if (features.passiveA.contains(PassiveAbilities.FULL_FLIGHT))
                    ev.player.getAbilities().mayfly = false;
            }
        }

        if(!ev.player.level().isClientSide){
            doEffects(ev);
            if(features.passiveA.contains(PassiveAbilities.WATER_CONDUIT)){
                if(ev.player.getAirSupply() != ev.player.getMaxAirSupply())
                    ev.player.setAirSupply(ev.player.getMaxAirSupply());
            }
        }
    }
    public void handleWearerHurt(LivingHurtEvent ev) {
        var damage = ev.getAmount();
        var type = ev.getSource();

        if(!isWeak(ev)) {
            if (features.currentPower > damage * 2) {
                features.removePower(damage * 2);
                ev.setAmount(0);
            } else {
                ev.setAmount(damage - (features.currentPower / 2));
                features.setPower(0);
            }
        }
    }
    public void handleDamagedEntity(LivingHurtEvent ev){
        if(features.chargeType == ChargeType.LIFE_DRAIN)
            features.addPower(ev.getAmount() * features.pRechargePS);
    }
    public void handleFallDamage(LivingFallEvent ev) {

        if (ev.getDistance() > features.fallDmgCancDist)
            ev.setDistance(ev.getDistance() - features.fallDmgCancDist);
        else
            ev.setDistance(0);

        ev.setDamageMultiplier(features.fallDmgMult);
    }
    public void handleEndermanAnger(EnderManAngerEvent ev){
        if(features.passiveA.contains(PassiveAbilities.BLOCK_ENDERMAN_LOOK)) {
            ev.setResult(Event.Result.DENY);
            ev.setCanceled(true);
        }
    }

    public void tryChargeArmour(TickEvent.PlayerTickEvent ev){
        switch (features.chargeType){
            case ON_GROUND: {
                if(ev.player.onGround()){
                    if (features.currentPower != features.maxPower) {
                        features.addPower(features.pRechargePS / 20);
                    }
                }
            }
            case IN_FIRE:
            case IN_FIRE_OR_LAVA: {
                if(ev.player.isOnFire() || ev.player.isInLava()){
                    if (features.currentPower != features.maxPower) {
                        features.addPower(features.pRechargePS / 20);
                    }
                }
            }

            case IN_WATER:{
                if(ev.player.isUnderWater() || (ev.player.isInWaterOrRain() && ev.player.onGround())){
                    if (features.currentPower != features.maxPower) {
                        features.addPower(features.pRechargePS / 20);
                    }
                }
            }
        }
    }
    public void doFullFlight(TickEvent.PlayerTickEvent ev){
        if (features.passiveA.contains(PassiveAbilities.FULL_FLIGHT)) {
            if (features.currentPower > 0)
                ev.player.getAbilities().mayfly = true;
            else {
                ev.player.getAbilities().flying = false;
                ev.player.getAbilities().mayfly = false;
            }
        }
    }
    public void doLimitedFlight(TickEvent.PlayerTickEvent ev){
        if (features.canLimitedFlight() && ev.player.isFallFlying() && !ev.player.isInWater()) {
            Vec3 lAng = ev.player.getLookAngle();
            Vec3 mov = ev.player.getDeltaMovement();

            ev.player.setDeltaMovement(
                    lAng.x * features.flightVelocity + (lAng.x * 1.5D - mov.x) * 0.8D,
                    lAng.y * features.flightVelocity + (lAng.y * 1.5D - mov.y) * 0.8D,
                    lAng.z * features.flightVelocity + (lAng.z * 1.5D - mov.z) * 0.8D);
        }
    }
    public void doEffects(TickEvent.PlayerTickEvent ev){
        for (MobEffect effect : features.effects) {
            if(effect == MobEffects.DOLPHINS_GRACE || effect == MobEffects.CONDUIT_POWER || effect == MobEffects.WATER_BREATHING){
                ev.player.addEffect(new MobEffectInstance(effect, 220, 3));
            }
            else {
                ev.player.addEffect(new MobEffectInstance(effect, 220));
            }
        }
    }

    public void shootProjectile(Level lvl, ServerPlayer plr, Projectile proj, int powerDrain){
        shootProjectile(lvl, plr, proj, powerDrain, null, 0);
    }
    public void shootProjectile(Level lvl, ServerPlayer plr, Projectile proj, int powerDrain, Item removalItem){
        shootProjectile(lvl, plr, proj, powerDrain, removalItem, 1);
    }
    public void shootProjectile(Level lvl, ServerPlayer plr, Projectile proj, int powerDrain, Item removalItem, int removalCount) {
        if(features.projCooldown > 0){
            proj.remove(Entity.RemovalReason.DISCARDED);
        }
        else {
            try {
                features.removePower(powerDrain);
                features.projCooldown = 10;

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
    }
    public void sentryMode(Level lvl, ServerPlayer plr) {
        if(lvl.isClientSide || !features.activeA.contains(ActiveAbilities.SENTRY_MODE))
            return;

        try {
            OSSManager.SpawnSentry(plr, this.features.getModelName());

            plr.getInventory().armor.clear();
        } catch (Exception e) {
            plr.server.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }
    public void teleport(Level lvl, ServerPlayer plr){
        if(lvl.isClientSide || !features.activeA.contains(ActiveAbilities.TELEPORT))
            return;

        var block = plr.pick(50, 1, false);
        if(block.getType() == HitResult.Type.BLOCK){
            var cost = new BigDecimal(block.getLocation().distanceTo(plr.position())).setScale(2, RoundingMode.UP).floatValue() * 3;
            plr.displayClientMessage(Component.literal("EEEE " + cost + " E "), false);

            if(features.hasPower(cost))
            {
                plr.teleportTo(block.getLocation().x, block.getLocation().y, block.getLocation().z);
                features.removePower(cost);
            }
        }



    }
    public void sonicBoom(Level lvl, ServerPlayer plr){
        if(lvl.isClientSide || !features.activeA.contains(ActiveAbilities.SONIC_BOOM))
            return;

        features.removePower(150);
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
    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        if(entity instanceof Player plr && hasChestplate(plr) && hasBoots(plr)
                && features.canLimitedFlight()) {
            return true;
        }
        else
            return false;
    }
    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if(!entity.level().isClientSide())
            entity.gameEvent(GameEvent.ELYTRA_GLIDE);

        return true;
    }

    //Client only
    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }
    @Override
    public void renderGUI(RenderGuiEvent event, PoseStack matrix) {
        var instance = Minecraft.getInstance();
        var player = instance.player;


        if (instance.isPaused() || player == null || instance.options.hideGui)
            return;

        int x = event.getGuiGraphics().guiWidth();
        int y = event.getGuiGraphics().guiHeight();
        int color = Integer.parseInt("33AA66", 16);

        event.getGuiGraphics().drawString(instance.font, "Power: " + features.currentPower + "/" + features.maxPower, x - 75, y - 25, color);
    }
    @Override
    public void appendHoverText(@NotNull ItemStack item, Level level, List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        components.add(Component.translatable("tooltip.powersuits." + features.getModelName() + ".identifier"));
        components.add(Component.translatable("tooltip.powersuits." + features.getModelName() + ".extra"));
    }
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private SuitArmourRenderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) {
                    this.renderer = new SuitArmourRenderer();
                }

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    public void clientArmourTick(TickEvent.ClientTickEvent ev, Player player) {
        if (hasFullSet(player)) {
            if(KeyBinding.ABILITY_1.consumeClick()){
                if(0 < features.activeA.size())
                    ModMessages.sendToServer(new C2SSuitAbility(0, features.activeA.get(0).getValue()));
            }
            if(KeyBinding.ABILITY_2.consumeClick()){
                if(1 < features.activeA.size())
                    ModMessages.sendToServer(new C2SSuitAbility(1, features.activeA.get(1).getValue()));
            }
            if(KeyBinding.ABILITY_3.consumeClick()){
                if(2 < features.activeA.size())
                    ModMessages.sendToServer(new C2SSuitAbility(2, features.activeA.get(2).getValue()));
            }
            if(KeyBinding.ABILITY_4.consumeClick()){
                if(3 < features.activeA.size())
                    ModMessages.sendToServer(new C2SSuitAbility(3, features.activeA.get(3).getValue()));
            }
        }
    }

    public void waterDash(Level lvl, Player plr){
        features.removePower(30);

        float f7 = plr.getYRot();
        float f = plr.getXRot();
        float f1 = -Mth.sin(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
        float f2 = -Mth.sin(f * ((float)Math.PI / 180F));
        float f3 = Mth.cos(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
        float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
        float f5 = 3.0F * ((1.0F + (float)2) / 4.0F);
        f1 *= f5 / f4;
        f2 *= f5 / f4;
        f3 *= f5 / f4;
        plr.push((double)f1, (double)f2, (double)f3);
        plr.startAutoSpinAttack(20);
        if (plr.onGround()) {
            float f6 = 1.1999999F;
            plr.move(MoverType.SELF, new Vec3(0.0D, (double)1.1999999F, 0.0D));
        }
    }
}
