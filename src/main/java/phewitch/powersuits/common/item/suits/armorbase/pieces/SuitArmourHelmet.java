package phewitch.powersuits.common.item.suits.armorbase.pieces;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.eventbus.api.Event;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;

public class SuitArmourHelmet extends SuitArmourBase {
    public SuitArmourHelmet(ArmorMaterial materialIn, Type type, Properties properties, SuitFeatures features) {
        super(materialIn, type, properties, features);
    }

    public void handleEndermanAnger(EnderManAngerEvent ev){
        if(features.passiveA.contains(PassiveAbilities.BLOCK_ENDERMAN_LOOK)) {
            ev.setResult(Event.Result.DENY);
            ev.setCanceled(true);
        }
    }
}
