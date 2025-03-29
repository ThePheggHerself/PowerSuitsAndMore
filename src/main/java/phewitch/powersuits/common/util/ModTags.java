package phewitch.powersuits.common.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import phewitch.powersuits.PowerSuits;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> ORE = tag("ore");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(PowerSuits.MODID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> ORE = commonTag("ores");
        public static final TagKey<Item> INGOT = commonTag("ingots");
        public static final TagKey<Item> PLATES = commonTag("plates");
        public static final TagKey<Item> COMP = tag("components");
        public static final TagKey<Item> COMP_WEAPONS = tag("components/weapons");
        public static final TagKey<Item> COMP_FLIGHT = tag("components/flight");
        public static final TagKey<Item> COMP_POWER = tag("components/power");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(PowerSuits.MODID, name));
        }

        public static TagKey<Item> commonTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
