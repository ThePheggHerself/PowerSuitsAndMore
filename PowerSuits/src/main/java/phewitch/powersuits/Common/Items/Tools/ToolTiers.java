package phewitch.powersuits.Common.Items.Tools;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import phewitch.powersuits.Common.Items.Materials.Titanium;
import phewitch.powersuits.PowerSuits;

import java.util.List;

public class ToolTiers {
    public static final Tier TITANIUM = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2250, 15, 3f, 10,
                    BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Titanium.TITANIUM_INGOT.get())),
            new ResourceLocation(PowerSuits.MODID, "titanium"), List.of(Tiers.NETHERITE), List.of());
}
