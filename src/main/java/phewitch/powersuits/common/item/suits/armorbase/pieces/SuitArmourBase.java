package phewitch.powersuits.common.item.suits.armorbase.pieces;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.client.gui.GUIManager;
import phewitch.powersuits.client.gui.IHUDItem;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourRenderer;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.function.Consumer;

public class SuitArmourBase extends ArmorItem implements GeoItem, IHUDItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public SuitFeatures features;

    public SuitArmourBase(ArmorMaterial materialIn, Type type, Properties properties, SuitFeatures features) {
        super(materialIn, type, properties);
        this.features = features;

        GUIManager.registerHUDItem(features.getModelName() + "_armor", this);
    }

    public static SuitArmourBase isWearingAnyPiece(Player plr){
        for (var i : plr.getInventory().armor){
            if(i.getItem() instanceof SuitArmourBase sAB)
                return sAB;
        }

        return null;
    }

    public static @Nullable SuitArmourHelmet getHelmet(Player player) {
        if(player != null && (player.getInventory().getArmor(3).getItem() instanceof SuitArmourHelmet ai))
            return ai;

        return null;
    }
    public static @Nullable SuitArmourChest getChestplate(Player player){
        if(player != null && (player.getInventory().getArmor(2).getItem() instanceof SuitArmourChest ai))
            return ai;

        return null;
    }
    public static @Nullable SuitArmourLegs getLeggings(Player player){
        if(player != null && (player.getInventory().getArmor(1).getItem() instanceof SuitArmourLegs ai))
            return ai;

        return null;
    }
    public static @Nullable SuitArmourBoots getBoots(Player player){
        if(player != null && (player.getInventory().getArmor(0).getItem() instanceof SuitArmourBoots ai))
            return ai;

        return null;
    }
    public static @Nullable SuitArmourBase getAny(Player player){
        SuitArmourBase sAB = getHelmet(player);
        if(sAB != null)
            return sAB;

        sAB = (SuitArmourBase) getChestplate(player);
        if(sAB != null)
            return sAB;

        sAB = (SuitArmourBase) getLeggings(player);
        if(sAB != null)
            return sAB;

        sAB = (SuitArmourBase) getBoots(player);
        if(sAB != null)
            return sAB;

        return null;
    }

    public static Boolean hasHelmet(Player player) { return getHelmet(player) != null; }
    public static Boolean hasChestplate(Player player) { return getChestplate(player) != null; }
    public static Boolean hasLegs(Player player) { return getLeggings(player) != null; }
    public static Boolean hasBoots(Player player) { return getBoots(player) != null; }
    public static boolean hasAny(Player player) { return getAny(player) != null; }

    public static Boolean hasFullSet(Player player) {
        if (player == null)
            return false;

        return hasBoots(player) && hasLegs(player) && hasChestplate(player) && hasHelmet(player);
    }
    public static boolean hasBootsOrChestplate(Player player) {
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

                if(features.projCooldown > 0)
                    features.projCooldown -= 1;
            } else {
                if (features.passiveA.contains(PassiveAbilities.FULL_FLIGHT))
                    ev.player.getAbilities().mayfly = false;
            }
        }

        if(!ev.player.level().isClientSide){
            if(hasFullSet(ev.player)) {
                tryChargeArmour(ev);
                doEffects(ev);
            }
            if(features.passiveA.contains(PassiveAbilities.WATER_CONDUIT)){
                if(ev.player.getAirSupply() != ev.player.getMaxAirSupply())
                    ev.player.setAirSupply(ev.player.getMaxAirSupply());
            }
        }
    }
    public void handleWearerHurt(LivingHurtEvent ev) {
        var damage = ev.getAmount();
        var type = ev.getSource();
        var player = (ServerPlayer) ev.getEntity();
        var chestplate = getChestplate(player);

        if(chestplate != null && !isWeak(ev)) {
            if (chestplate.getEnergy(player) > damage * 2) {
                chestplate.dischargeArmor((int)(damage * 10), player);
                ev.setAmount(0);
            }
        }
    }
    public void handleDamagedEntity(LivingHurtEvent ev){
        var player = (ServerPlayer) ev.getEntity();
        var chestplate = getChestplate(player);

        if(chestplate != null && features.chargeType == ChargeType.LIFE_DRAIN)
            chestplate.chargeArmor((int)(ev.getAmount() * features.recharge), player);
    }

    public void tryChargeArmour(TickEvent.PlayerTickEvent ev){
        if(this instanceof SuitArmourChest sAC){
            switch (features.chargeType) {
                case ON_GROUND: {
                    if (ev.player.onGround()) {
                        if (features.currentPower != features.maxPower) {
                            sAC.chargeArmor(features.recharge, (ServerPlayer) ev.player);
                        }
                    }
                }
                case IN_FIRE:
                case IN_FIRE_OR_LAVA: {
                    if (ev.player.isOnFire() || ev.player.isInLava()) {
                        if (features.currentPower != features.maxPower) {
                            sAC.chargeArmor(features.recharge, (ServerPlayer) ev.player);
                        }
                    }
                }

                case IN_WATER: {
                    if (ev.player.isUnderWater() || (ev.player.isInWaterOrRain() && ev.player.onGround())) {
                        if (features.currentPower != features.maxPower) {
                            sAC.chargeArmor(features.recharge, (ServerPlayer) ev.player);
                        }
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
    public void waterDash(Level lvl, Player plr){
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
