package phewitch.powersuits.common.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.PowerSuits;

import java.util.List;

public class ToolsManager {
    public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(ForgeRegistries.ITEMS, PowerSuits.MODID);

    public static final RegistryObject<Item> TITANIUM_SWORD = TOOLS.register("titanium_sword", () ->
            new SwordItem(ToolTiers.TITANIUM, 2, 3, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_SHOVEL = TOOLS.register("titanium_shovel", () ->
            new ShovelItem(ToolTiers.TITANIUM, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_AXE = TOOLS.register("titanium_axe", () ->
            new AxeItem(ToolTiers.TITANIUM, 4, 0, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_PICKAXE = TOOLS.register("titanium_pickaxe", () ->
            new PickaxeItem(ToolTiers.TITANIUM, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_HOE = TOOLS.register("titanium_hoe", () ->
            new HoeItem(ToolTiers.TITANIUM, 0, 0, new Item.Properties()));




    public class ToolTiers {
        public static final Tier TITANIUM = TierSortingRegistry.registerTier(
                new ForgeTier(5, 2250, 15, 3f, 10,
                        BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemsManager.TITANIUM_INGOT.get())),
                new ResourceLocation(PowerSuits.MODID, "titanium"), List.of(Tiers.NETHERITE), List.of());
    }

}
