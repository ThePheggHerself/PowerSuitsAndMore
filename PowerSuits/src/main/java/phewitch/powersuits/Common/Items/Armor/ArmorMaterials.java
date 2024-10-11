package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import phewitch.powersuits.Common.Items.Materials.Titanium;
import phewitch.powersuits.Common.Items.Materials.Steel;
import phewitch.powersuits.PowerSuits;

import java.util.function.Supplier;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.EnumMap;
import java.util.function.Supplier;

public enum ArmorMaterials implements ArmorMaterial {
    TITANIUM("titanium", 24, new int[]{3, 5, 7, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F,
            0.0F, () -> { return Ingredient.of(Titanium.TITANIUM_INGOT.get());
    }),
    MARK1("mark1", 15, new int[]{4, 7, 8, 5}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.0f,
            0.3F, () -> { return Ingredient.of(Blocks.BEDROCK);
    }),

    MARK2("mark2", 15, new int[]{5, 8, 9, 6}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.1f,
            0.4F, () -> { return Ingredient.of(Blocks.BEDROCK);
    }),

    MARK3("mark3", 15, new int[]{7, 10, 11, 8}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.2f,
            0.5F, () -> { return Ingredient.of(Blocks.BEDROCK);
    }),

    MARK4("mark4", 15, new int[]{10, 13, 14, 11}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.4f,
            0.7F, () -> { return Ingredient.of(Blocks.BEDROCK);
    }),
    MARK5("mark5", 15, new int[]{14, 17, 18, 15}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.8f,
            1F, () -> { return Ingredient.of(Blocks.BEDROCK);
    });
    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 16, 13 };

    ArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return PowerSuits.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
