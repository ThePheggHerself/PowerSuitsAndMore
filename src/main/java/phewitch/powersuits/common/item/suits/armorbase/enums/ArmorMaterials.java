package phewitch.powersuits.common.item.suits.armorbase.enums;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.item.ItemsManager;

import java.util.function.Supplier;

public enum ArmorMaterials implements ArmorMaterial {
    TITANIUM("titanium", 24, new int[]{3, 5, 7, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F,
            0.0F, () -> Ingredient.of(ItemsManager.TITANIUM_INGOT.get())),
    MARK1("mark1", 1, new int[]{4, 7, 8, 5}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.5f,
            0.5F, () -> Ingredient.of(ItemsManager.STEEL_PLATE.get())),
    MARK2("mark2", 1, new int[]{5, 8, 9, 6}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 1f,
            1F, () -> Ingredient.of(Blocks.BEDROCK)),
    MARK3("mark3", 1, new int[]{7, 10, 11, 8}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.5f,
            2.5F, () -> Ingredient.of(Blocks.BEDROCK)),
    MARK4("mark4", 1, new int[]{10, 13, 14, 11}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 5f,
            5F, () -> Ingredient.of(Blocks.BEDROCK)),
    MARK5("mark5", 1, new int[]{15, 18, 19, 16}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 5f,
            5F, () -> Ingredient.of(Blocks.BEDROCK)),
    MARK6("mark6", 1, new int[]{13, 16, 17, 14}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 4f,
            3F, () -> Ingredient.of(Blocks.BEDROCK)),
    MARK7("mark7", 1, new int[]{17, 20, 21, 18}, 0, SoundEvents.ARMOR_EQUIP_NETHERITE, 6f,
            6F, () -> Ingredient.of(Blocks.BEDROCK));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private static final int[] BASE_DURABILITY = {11, 16, 16, 13};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

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
