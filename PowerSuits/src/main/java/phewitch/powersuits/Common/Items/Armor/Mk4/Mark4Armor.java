package phewitch.powersuits.Common.Items.Armor.Mk4;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Renderer;
import phewitch.powersuits.Common.Items.Armor.Suits;

import java.util.function.Consumer;

public class Mark4Armor extends SuitArmourBase {
    public Mark4Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mk4");
        this.fallDamageMultiplier = 0;
        projectileDamage = 5;
        shootsLasers = false;
        shootsArrows = true;
    }

    @Override
    public void onArmorTick(ItemStack item, Level level, Player player) {
        if(hasHelmet(player) && item.getItem() == Suits.MK4_HELM.get()){
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 5));
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 5));
        }

        if(hasFullSet(player)){
            player.getAbilities().mayfly = true;
        }
        else {
            player.getAbilities().mayfly = false;

            if(hasBootsOrChestplate(player)) {
                if (Minecraft.getInstance().options.keyJump.isDown()) {
                    var motion = player.getDeltaMovement();
                    var upwardsVelocity = motion.get(Direction.Axis.Y);
                    upwardsVelocity += 0.1d;

                    if (upwardsVelocity > 1)
                        upwardsVelocity = 1;

                    player.setDeltaMovement(motion.get(Direction.Axis.X), upwardsVelocity, motion.get(Direction.Axis.Z));
                }
            }
        }
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private Mark4Renderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if(this.renderer == null){
                    this.renderer = new Mark4Renderer();
                }

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }
}
