package phewitch.powersuits.common.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.item.suits.armorbase.ArmorMaterials;
import phewitch.powersuits.PowerSuits;

public class ArmorManager {

    public static final DeferredRegister<Item> ARMOR = DeferredRegister.create(ForgeRegistries.ITEMS, PowerSuits.MODID);

    public static final RegistryObject<Item> TITANIUM_HELMET = ARMOR.register("titanium_helmet", () ->
            new ArmorItem(ArmorMaterials.TITANIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ARMOR.register("titanium_chestplate", () ->
            new ArmorItem(ArmorMaterials.TITANIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_LEGGINGS = ARMOR.register("titanium_leggings", () ->
            new ArmorItem(ArmorMaterials.TITANIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_BOOTS = ARMOR.register("titanium_boots", () ->
            new ArmorItem(ArmorMaterials.TITANIUM, ArmorItem.Type.BOOTS, new Item.Properties()));
}
