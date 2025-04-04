package phewitch.powersuits.common.item.suits.armorbase.pieces;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.client.data.ClientData;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourRenderer;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.client2server.C2SSuitQuickLaunch;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class SuitArmourBase extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public SuitFeatures features;

    public SuitArmourBase(ArmorMaterial materialIn, Type type, Properties properties, SuitFeatures features) {
        super(materialIn, type, properties);
        this.features = features;
    }

    public static @Nullable SuitArmourHelmet getHelmet(Player player) {
        if (player != null && (player.getInventory().getArmor(3).getItem() instanceof SuitArmourHelmet ai))
            return ai;

        return null;
    }
    public static @Nullable SuitArmourChest getChestplate(Player player) {
        if (player != null && (player.getInventory().getArmor(2).getItem() instanceof SuitArmourChest ai))
            return ai;

        return null;
    }
    public static @Nullable SuitArmourLegs getLeggings(Player player) {
        if (player != null && (player.getInventory().getArmor(1).getItem() instanceof SuitArmourLegs ai))
            return ai;

        return null;
    }
    public static @Nullable SuitArmourBoots getBoots(Player player) {
        if (player != null && (player.getInventory().getArmor(0).getItem() instanceof SuitArmourBoots ai))
            return ai;

        return null;
    }
    public static @Nullable SuitArmourBase getAny(Player player) {
        SuitArmourBase sAB = getHelmet(player);
        if (sAB != null)
            return sAB;

        sAB = getChestplate(player);
        if (sAB != null)
            return sAB;

        sAB = getLeggings(player);
        if (sAB != null)
            return sAB;

        sAB = getBoots(player);
        return sAB;
    }

    public static Boolean hasHelmet(Player player) {
        return getHelmet(player) != null;
    }
    public static Boolean hasChestplate(Player player) {
        return getChestplate(player) != null;
    }
    public static Boolean hasLegs(Player player) {
        return getLeggings(player) != null;
    }
    public static Boolean hasBoots(Player player) {
        return getBoots(player) != null;
    }
    public static Boolean hasAny(Player player) {
        return getAny(player) != null;
    }
    public static Boolean hasFullSet(Player player) {
        if (player == null)
            return false;

        return hasBoots(player) && hasLegs(player) && hasChestplate(player) && hasHelmet(player);
    }

    public Boolean isWeak(LivingHurtEvent ev) {
        var type = ev.getSource();
        if (features.weaknesses.contains(Weakness.FIRE) &&
                (type.is(DamageTypes.IN_FIRE) || type.is(DamageTypes.ON_FIRE) || type.is(DamageTypes.FIREBALL) || type.is(DamageTypes.LAVA)))
            return true;

        return features.weaknesses.contains(Weakness.WATER) &&
                (ev.getEntity().isInWater() || type.is(DamageTypes.DROWN));
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    public void tryTickSuit(TickEvent.PlayerTickEvent ev){
        Optional.ofNullable(getHelmet(ev.player)).ifPresent( helm -> helm.onArmorPieceTick(ev));
        Optional.ofNullable(getChestplate(ev.player)).ifPresent( chest -> chest.onArmorPieceTick(ev));
        Optional.ofNullable(getLeggings(ev.player)).ifPresent( legs ->  legs.onArmorPieceTick(ev));
        Optional.ofNullable(getBoots(ev.player)).ifPresent( boots -> boots.onArmorPieceTick(ev));
    }

    int sprintTicks = 30;
    float extraVelocity = 0;
    public void handleWearerHurt(LivingHurtEvent ev) {
        var damage = ev.getAmount();
        var player = (ServerPlayer) ev.getEntity();
        var chestplate = getChestplate(player);

        if (chestplate != null && !isWeak(ev)) {
            if (chestplate.getEnergy(player) > damage * 2) {
                chestplate.dischargeArmor((int) (damage * 10), player);
                ev.setAmount(0);
            }
        }
    }
    public void handleDamagedEntity(LivingHurtEvent ev) {
        var player = (ServerPlayer) ev.getSource().getEntity();
        var chestplate = getChestplate(player);

        if (chestplate != null && features.chargeType == ChargeType.LIFE_DRAIN)
            chestplate.chargeArmor((int) (ev.getAmount() * features.recharge), player);
    }
    public void doLimitedFlight(TickEvent.PlayerTickEvent ev) {
        System.out.println(sprintTicks);

        if (ev.player.isFallFlying() && !ev.player.isInWater() && ClientData.suitPower > 0) {
            Vec3 lAng = ev.player.getLookAngle();
            Vec3 mov = ev.player.getDeltaMovement();

            if(ev.player.isSprinting())
            {
                if(sprintTicks == 30){
                    if(ClientData.suitPower > 500) {
                        ModMessages.sendToServer(new C2SSuitQuickLaunch());
                        extraVelocity = 5;
                    }
                    else {
                        sprintTicks = 0;
                    }
                }

                ev.player.setDeltaMovement(
                        lAng.x * features.flightVelocity * Math.min(extraVelocity, 1) + (lAng.x * 1.5D - mov.x) * 0.8D,
                        lAng.y * features.flightVelocity * Math.min(extraVelocity, 1) + (lAng.y * 1.5D - mov.y) * 0.8D,
                        lAng.z * features.flightVelocity * Math.min(extraVelocity, 1) + (lAng.z * 1.5D - mov.z) * 0.8D);

                if(sprintTicks > 0) {
                    sprintTicks--;
                }
                else if(extraVelocity > 1){
                    extraVelocity -= 0.1f;
                }
                else {
                    ev.player.setSprinting(false);
                }
            }
            else {
                ev.player.setDeltaMovement(
                        lAng.x * features.flightVelocity + (lAng.x * 1.5D - mov.x) * 0.8D,
                        lAng.y * features.flightVelocity + (lAng.y * 1.5D - mov.y) * 0.8D,
                        lAng.z * features.flightVelocity + (lAng.z * 1.5D - mov.z) * 0.8D);
            }
        }

        else {
            if(sprintTicks < 30 && ev.player.onGround())
                sprintTicks = Math.min(sprintTicks+1, 30);
        }
    }
    public void doEffects(TickEvent.PlayerTickEvent ev) {
        for (MobEffect effect : features.effects) {
            if (effect == MobEffects.DOLPHINS_GRACE || effect == MobEffects.CONDUIT_POWER || effect == MobEffects.WATER_BREATHING) {
                ev.player.addEffect(new MobEffectInstance(effect, 220, 3));
            } else {
                ev.player.addEffect(new MobEffectInstance(effect, 220));
            }
        }
    }

    public void onArmorEquipped(LivingEquipmentChangeEvent ev){}
    public void onArmorUnequipped(LivingEquipmentChangeEvent ev){}
    public void onArmorPieceTick(TickEvent.PlayerTickEvent ev) {}

    //Client only
    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
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

    public void waterDash(Level lvl, Player plr) {
        float f7 = plr.getYRot();
        float f = plr.getXRot();
        float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
        float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
        float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
        float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
        float f5 = 3.0F * ((1.0F + (float) 2) / 4.0F);
        f1 *= f5 / f4;
        f2 *= f5 / f4;
        f3 *= f5 / f4;
        plr.push(f1, f2, f3);
        plr.startAutoSpinAttack(20);
        if (plr.onGround()) {
            float f6 = 1.1999999F;
            plr.move(MoverType.SELF, new Vec3(0.0D, 1.1999999F, 0.0D));
        }
    }
}
