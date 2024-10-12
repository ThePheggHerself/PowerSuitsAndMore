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

public class Mark2Armor extends SuitArmourBase {
    public float flightTime = 300;
    public float maxFuel = 300;
    public Mark2Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark2");

        features.add(SuitFeatures.LIMITED_FLIGHT);
        features.add(SuitFeatures.SHOOT_ARROWS);
        fallDamageMultiplier = 0.3f;
        projectileDamage = 3;
        lfMaxfuel = 200f;
        lfVelocity = 0.15d;
    }
}
