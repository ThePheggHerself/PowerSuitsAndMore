package phewitch.powersuits.common.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModBlock extends Block {
    public String registrySuffix;

    public TagKey<Block> tag;
    public ModBlock(String suffix, TagKey<Block> itag, Properties pProperties) {
        super(pProperties);
        registrySuffix = suffix;
        tag = itag;
    }
}
