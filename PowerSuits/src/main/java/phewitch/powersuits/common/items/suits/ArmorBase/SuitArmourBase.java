package phewitch.powersuits.common.items.suits.ArmorBase;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.client.KeyBinding;
import phewitch.powersuits.client.gui.GUIManager;
import phewitch.powersuits.client.gui.IHUDItem;
import phewitch.powersuits.common.entity.EntityManager;
import phewitch.powersuits.common.entity.OSSManager;
import phewitch.powersuits.common.entity.projectiles.ChestLaserProjectile;
import phewitch.powersuits.common.entity.projectiles.LaserProjectile;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.client2server.C2SSuitSentryMode;
import phewitch.powersuits.common.networking.packets.client2server.C2SSuitShootArrow;
import phewitch.powersuits.common.networking.packets.client2server.C2SSuitShootChestLaser;
import phewitch.powersuits.common.networking.packets.client2server.C2SSuitShootLaser;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.ArrayList;
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

    public ArrayList<Integer> getsuitAbilities() {
        ArrayList<Integer> aList = new ArrayList<>();
        for (var a : features.abilities) {
            aList.add(a.getValue());
        }

        return aList;
    }

    public void handleClientFeatures(TickEvent.ClientTickEvent ev, Player player) {
        if (hasFullSet(player)) {
            if (features.abilities.contains(SuitFeatures.ABILITIES.SHOOT_ARROWS) && KeyBinding.SHOOT_ARROW_KEY.consumeClick()) {
                if (player.getInventory().contains(new ItemStack(Items.ARROW))) {
                    ModMessages.sendToServer(new C2SSuitShootArrow());
                } else {
                    player.displayClientMessage(Component.translatable("msg." + PowerSuits.MODID + ".suit.noarrows"), true);
                }
            }

            if (features.abilities.contains(SuitFeatures.ABILITIES.SHOOT_LASERS) && KeyBinding.SHOOT_LASER_KEY.consumeClick()) {
                var curTime = System.currentTimeMillis();
                if (features.lastLaserShot < 1 || curTime - features.lastLaserShot > 500) {
                    ModMessages.sendToServer(new C2SSuitShootLaser());

                    features.lastLaserShot = curTime;
                }
            }

            if (features.abilities.contains(SuitFeatures.ABILITIES.SHOOT_CHEST_LASER) && KeyBinding.SHOOT_CHEST_LASER_KEY.consumeClick()) {
                var curTime = System.currentTimeMillis();
                if (features.lastChestLaserShot < 1 || curTime - features.lastChestLaserShot > 5000) {
                    ModMessages.sendToServer(new C2SSuitShootChestLaser());

                    features.lastChestLaserShot = curTime;
                }
            }

            if (features.abilities.contains(SuitFeatures.ABILITIES.SENTRY_MODE) && KeyBinding.SENTRY_MODE_KEY.consumeClick()) {
                ModMessages.sendToServer(new C2SSuitSentryMode());
            }
        }
    }
    public void playerTickHandler(TickEvent.PlayerTickEvent ev) {
        //ev.player.displayClientMessage(Component.literal("Power: " + String.format("%.0f", features.currentPower) + "/" + String.format("%.0f", features.maxPower)), true);

        if (hasFullSet(ev.player)) {
            if(ev.player.onGround()){
                if (features.currentPower != features.maxPower) {
                    features.addPower(features.powerRechargePerSecond / 20);
                }
            }

            if (features.abilities.contains(SuitFeatures.ABILITIES.FULL_FLIGHT)) {
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

            for (MobEffect effect : features.fullArmourEffects) {
                ev.player.addEffect(new MobEffectInstance(effect, 220));
            }

        } else {
            if (features.abilities.contains(SuitFeatures.ABILITIES.FULL_FLIGHT))
                ev.player.getAbilities().mayfly = false;
        }
    }



    public void handleFallDamage(LivingFallEvent ev) {

        if (ev.getDistance() > features.fallDamageCancellationDistance)
            ev.setDistance(ev.getDistance() - features.fallDamageCancellationDistance);
        else
            ev.setDistance(0);

        ev.setDamageMultiplier(features.fallDamageMultiplier);
    }

    public void handleHurt(LivingHurtEvent ev) {
        var damage = ev.getAmount();
        if (features.currentPower > damage * 2) {
            features.removePower(damage * 2);
            ev.setAmount(0);
        } else {
            ev.setAmount(damage - (features.currentPower / 2));
            features.setPower(0);
        }
    }

    public void shootLaser(Level lvl, ServerPlayer plr) {
        if (!features.abilities.contains(SuitFeatures.ABILITIES.SHOOT_LASERS) || !features.hasPower(features.laserShotCost))
            return;

        try {
            var proj = new LaserProjectile(EntityManager.LASER_PROJECTILE.get(), plr, lvl);
            proj.setPos(plr.getX(), plr.getY() + 1.5, plr.getZ());
            proj.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0f, 3f, 1f);
            proj.tickCount = 50;
            lvl.addFreshEntity(proj);

            features.removePower(15);
        } catch (Exception e) {
            plr.server.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }

    public void shootChestLaser(Level lvl, ServerPlayer plr) {
        if (!features.abilities.contains(SuitFeatures.ABILITIES.SHOOT_CHEST_LASER) || !features.hasPower(features.chestLaserShotCost))
            return;

        try {
            var proj = new ChestLaserProjectile(EntityManager.CHEST_LASER_PROJECTILE.get(), plr, lvl);
            proj.setPos(plr.getX(), plr.getY() + 1.5, plr.getZ());
            proj.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0f, 5f, 1f);
            proj.tickCount = 50;
            lvl.addFreshEntity(proj);

            features.removePower(50f);
        } catch (Exception e) {
            plr.server.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }

    public void sentryMode(Level lvl, ServerPlayer plr) {
        if (!features.abilities.contains(SuitFeatures.ABILITIES.SENTRY_MODE))
            return;

        try {
            OSSManager.SpawnSentry(plr, this.features.getModelName());

            plr.getInventory().armor.clear();
        } catch (Exception e) {
            plr.server.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

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
