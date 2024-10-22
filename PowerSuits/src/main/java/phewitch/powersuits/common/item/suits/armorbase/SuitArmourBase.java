package phewitch.powersuits.common.item.suits.armorbase;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.phys.HitResult;
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
import phewitch.powersuits.common.entity.OSSManager;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.client2server.*;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Consumer;

public class SuitArmourBase extends ArmorItem implements GeoItem, IHUDItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public SuitFeatures features;

    public SuitArmourBase(ArmorMaterial materialIn, Type type, Properties properties, SuitTemplate template) {
        super(materialIn, type, properties);
        this.features = new SuitFeatures(template);

        GUIManager.registerHUDItem(features.getModelName() + "_armor", this);
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

    public void handleClientFeatures(TickEvent.ClientTickEvent ev, Player player) {
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
    public void playerTickHandler(TickEvent.PlayerTickEvent ev) {
        ev.player.displayClientMessage(Component.literal("Power: " + String.format("%.0f", features.currentPower) + "/" + String.format("%.0f", features.maxPower)), true);

        if (hasFullSet(ev.player)) {
            if(ev.player.onGround()){
                if (features.currentPower != features.maxPower) {
                    features.addPower(features.pRechargePS / 20);
                }
            }

            if (features.passiveA.contains(PassiveAbilities.FULL_FLIGHT)) {
                if (features.currentPower > 0)
                    ev.player.getAbilities().mayfly = true;
                else {
                    ev.player.getAbilities().flying = false;
                    ev.player.getAbilities().mayfly = false;
                }
            }
            if (features.canLimitedFlight() && hasBootsOrChestplate(ev.player)) {

                if (Minecraft.getInstance().options.keyJump.isDown()) {
                    var motion = ev.player.getDeltaMovement();
                    var upwardsVelocity = motion.get(Direction.Axis.Y);
                    upwardsVelocity += features.flightVelocity;

                    if (upwardsVelocity > 1)
                        upwardsVelocity = 1;

                    ev.player.setDeltaMovement(motion.get(Direction.Axis.X), upwardsVelocity, motion.get(Direction.Axis.Z));
                    if (features.flightCost > 0) {
                        features.removePower(features.flightCost / 20);
                    }
                }
            }

            for (MobEffect effect : features.effects) {
                if(effect == MobEffects.DOLPHINS_GRACE || effect == MobEffects.CONDUIT_POWER){
                    ev.player.addEffect(new MobEffectInstance(effect, 220, 3));
                }
                else {
                    ev.player.addEffect(new MobEffectInstance(effect, 220));
                }
            }

            if(features.projCooldown > 0)
                features.projCooldown -= 1;
        } else {
            if (features.passiveA.contains(PassiveAbilities.FULL_FLIGHT))
                ev.player.getAbilities().mayfly = false;
        }
    }
    public void handleHurt(LivingHurtEvent ev) {
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

    public void shootProjectile(Level lvl, ServerPlayer plr, Projectile proj, int powerDrain) {
        if(features.projCooldown > 0){
            proj.remove(Entity.RemovalReason.DISCARDED);
        }
        else {
            try {
                features.removePower(powerDrain);
                features.projCooldown = 10;

                proj.setPos(plr.getX(), plr.getY() + 1.5, plr.getZ());
                proj.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0f, 3f, 1f);
                proj.tickCount = 50;
                lvl.addFreshEntity(proj);
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
}
