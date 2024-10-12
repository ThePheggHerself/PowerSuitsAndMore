package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourRenderer;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitFeatures;

import java.util.function.Consumer;

public class Mark1Armor extends SuitArmourBase {
    public float flightTime = 50;
    public float maxFuel = 50;
    public Mark1Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark1");
        this.fallDamageMultiplier = 0.6f;

        features.add(SuitFeatures.LIMITED_FLIGHT);
        lfMaxfuel = 30f;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }


}
