package phewitch.powersuits.Common.Items.Tools;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ToolTiers {
    public static final ForgeTier Titanium = new ForgeTier(2, 1000, 12, 3f, 10, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(phewitch.powersuits.Common.Items.Materials.Titanium.TITANIUM_INGOT.get()));
}
