package phewitch.powersuits.Common.Items.Armor.Mk4;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.Suits;

import java.util.List;

public class Mark4Armor extends SuitArmourBase {
    public Mark4Armor(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder, "mk4");
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
    public void appendHoverText(ItemStack item, @Nullable Level p_41422_, List<Component> components, TooltipFlag p_41424_) {
        components.add(new TranslatableComponent("tooltip.powersuits." + name + ".identifier"));

        components.add(new TranslatableComponent("tooltip.powersuits." + name + ".extra"));
    }
}
