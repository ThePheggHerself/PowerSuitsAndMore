package phewitch.powersuits.Common.Items.Armor.Mk5;

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
import phewitch.powersuits.Common.Items.Armor.Suits;

import java.util.function.Consumer;

public class Mark5Armor extends SuitArmourBase {
    public Mark5Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mk5");
        this.fallDamageMultiplier = 0;
        projectileDamage = 5;
        shootsArrows = true;

        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.NIGHT_VISION, 300));
        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.WATER_BREATHING, 300));
        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300));
        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300));
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private Mark5Renderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if(this.renderer == null){
                    this.renderer = new Mark5Renderer();
                }

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }
}
