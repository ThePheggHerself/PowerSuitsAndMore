package phewitch.powersuits.Common.Items.Materials;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.Armor.ArmorMaterials;
import phewitch.powersuits.Common.Items.ItemManager;
import phewitch.powersuits.PowerSuits;

public class Steel {

    public static void register(IEventBus eventBus){

    }

    public static final RegistryObject<Item> STEEL_INGOT = ItemManager.ITEMS.register("steel_ingot", () ->
            new Item(new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Block> STEEL_BLOCK = ItemManager.registerBlock("steel_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), PowerSuits.CreativeTab);

    public static final RegistryObject<Item> STEEL_BOOTS = ItemManager.ITEMS.register("steel_boots", () ->
            new SteelBoots(ArmorMaterials.STEEL, EquipmentSlot.FEET, new Item.Properties().tab(PowerSuits.CreativeTab)));

    public static class SteelBoots extends ArmorItem{
        public SteelBoots(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
            super(material, slot, properties);
        }

        @Override
        public void onArmorTick(ItemStack stack, Level level, Player player) {
            var boots = player.getInventory().getArmor(0).getItem();

            if(boots instanceof ArmorItem AI){
                if(AI.getMaterial() == material) {
                    player.addEffect(new MobEffectInstance(MobEffects.JUMP, 1, 1));
                }
            }
            super.onArmorTick(stack, level, player);
        }
    }
}
