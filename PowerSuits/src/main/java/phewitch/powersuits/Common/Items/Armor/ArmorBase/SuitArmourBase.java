package phewitch.powersuits.Common.Items.Armor.ArmorBase;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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
import phewitch.powersuits.Client.GUI.GUIManager;
import phewitch.powersuits.Client.GUI.IHUDItem;
import phewitch.powersuits.Client.KeyBinding;
import phewitch.powersuits.Common.Entity.EntityManager;
import phewitch.powersuits.Common.Entity.Projectiles.ChestLaserProjectile;
import phewitch.powersuits.Common.Entity.Projectiles.LaserProjectile;
import phewitch.powersuits.Common.networking.ModMessages;
import phewitch.powersuits.Common.networking.Packets.C2SSuitShootArrow;
import phewitch.powersuits.Common.networking.Packets.C2SSuitShootChestLaser;
import phewitch.powersuits.Common.networking.Packets.C2SSuitShootLaser;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.function.Consumer;

public class SuitArmourBase extends ArmorItem implements GeoItem, IHUDItem {
    public final Minecraft minecraft = Minecraft.getInstance();
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public String name;
    public int projectileDamage = 7;
    SuitFeatures features;
    //The fall damage multiplier. 0 = no damage, 1 = 100% damage, 2 = 200% damage
    long lastLaserShot = 0;
    long lastChestLaserShot = 0;

    public SuitArmourBase(ArmorMaterial materialIn, Type type, Properties properties, String name, SuitFeatures features) {
        super(materialIn, type, properties);
        this.name = name;
        this.features = features;

        GUIManager.registerHUDItem(name + "_armor", this);
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
                if (lastLaserShot < 1 || curTime - lastLaserShot > 500) {
                    ModMessages.sendToServer(new C2SSuitShootLaser());

                    lastLaserShot = curTime;
                }
            }

            if (features.abilities.contains(SuitFeatures.ABILITIES.SHOOT_ARROWS) && KeyBinding.SHOOT_CHEST_LASER_KEY.consumeClick()) {
                var curTime = System.currentTimeMillis();
                if (lastChestLaserShot < 1 || curTime - lastChestLaserShot > 5000) {
                    ModMessages.sendToServer(new C2SSuitShootChestLaser());

                    lastChestLaserShot = curTime;
                }
            }
        }
    }
    public void playerTickHandler(TickEvent.PlayerTickEvent ev) {
        ev.player.displayClientMessage(Component.literal("Power: " + String.format("%.0f",features.currentPower) + "/" + String.format("%.0f",features.maxPower) ), true);

        if (hasChestplate(ev.player) && ev.player.onGround()) {

            if (features.currentPower != features.maxPower) {
                features.addPower(features.powerRechargePerSecond / 20);
            }
        }

        if (hasFullSet(ev.player)) {
            if (features.abilities.contains(SuitFeatures.ABILITIES.FULL_FLIGHT)) {
                if(features.currentPower > 0)
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

            for (MobEffectInstance effect : features.fullArmourEffects) {
                ev.player.addEffect(effect);
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

    public void handleHurt(LivingHurtEvent ev){
        var damage = ev.getAmount();
        if(features.currentPower > damage * 2){
            features.removePower(damage * 2);
            ev.setAmount(0);
        }
        else{
            ev.setAmount(damage - (features.currentPower /2));
            features.setPower(0);
        }
    }

    public void shootLaser(Level lvl, ServerPlayer plr){
        if(!features.abilities.contains(SuitFeatures.ABILITIES.SHOOT_LASERS))
            return;

        try {
            var proj = new LaserProjectile(EntityManager.LASER_PROJECTILE.get(), plr, lvl);
            proj.setPos(plr.getX(), plr.getY() + 1.5, plr.getZ());
            proj.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0f, 3f, 1f);
            proj.tickCount = 50;
            lvl.addFreshEntity(proj);

            features.removePower(15);
        }
        catch (Exception e){
            plr.server.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }

    public void shootChestLaser(Level lvl, ServerPlayer plr){
        if(!features.abilities.contains(SuitFeatures.ABILITIES.SHOOT_CHEST_LASER))
            return;

        try {
            var proj = new ChestLaserProjectile(EntityManager.CHEST_LASER_PROJECTILE.get(), plr, lvl);
            proj.setPos(plr.getX(), plr.getY() + 1.5, plr.getZ());
            proj.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0f, 5f, 1f);
            proj.tickCount = 50;
            lvl.addFreshEntity(proj);

            features.removePower(50f);
        }
        catch (Exception e){
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
        components.add(Component.translatable("tooltip.powersuits." + name + ".identifier"));
        components.add(Component.translatable("tooltip.powersuits." + name + ".extra"));
    }
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private SuitArmourRenderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) {
                    this.renderer = new SuitArmourRenderer(name);
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
