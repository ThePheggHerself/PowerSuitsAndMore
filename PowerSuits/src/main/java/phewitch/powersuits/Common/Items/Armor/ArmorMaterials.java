package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import phewitch.powersuits.Common.Items.Materials.Titanium;
import phewitch.powersuits.Common.Items.Materials.Steel;
import phewitch.powersuits.PowerSuits;

import java.util.function.Supplier;

public enum ArmorMaterials implements ArmorMaterial {
    TITANIUM("titanium", 24, new int[]{3, 5, 7, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F,
            0.0F, () -> { return Ingredient.of(Titanium.TITANIUM_INGOT.get());
    }),
    STEEL("steel", 15, new int[]{2, 5, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0f,
            0.0F, () -> { return Ingredient.of(Steel.STEEL_INGOT.get());
    }),
    MARK1("mark1", 15, new int[]{4, 7, 8, 5}, 0, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0f,
            0.3F, () -> { return Ingredient.of(Blocks.BEDROCK);
    }),

    MARK2("mark2", 15, new int[]{5, 8, 9, 6}, 0, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0f,
            0.4F, () -> { return Ingredient.of(Blocks.BEDROCK);
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private String name;
    private int durabilityMultiplier;
    private int[] slotProtections;
    private int enchantmentValue;
    private SoundEvent sound;
    private float toughness;
    private float knockbackResistance;
    private LazyLoadedValue<Ingredient> repairIngredient;

    ArmorMaterials(String name, int durabilityMultiplier, int[] slotProtection, int enchantValue, SoundEvent soundEvent, float toughness,
                   float knockbackResistance, Supplier<Ingredient> repairIngrediant) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtection;
        this.enchantmentValue = enchantValue;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngrediant);
    }

    ArmorMaterials() {
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return PowerSuits.MODID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
