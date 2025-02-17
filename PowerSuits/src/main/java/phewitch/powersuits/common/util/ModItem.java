package phewitch.powersuits.common.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItem extends Item {
    public String registrySuffix;

    public TagKey<Item> tag;

    public ModItem(String suffix, TagKey<Item> itag, Properties pProperties){
        super(pProperties);

        registrySuffix = suffix;
        tag = itag;
    }
}
