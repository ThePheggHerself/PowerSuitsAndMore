package phewitch.powersuits.common;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CommonCore {

    public static void RemoveItemFromInventory(Player player, Item item, Integer count){
        var inv = player.getInventory();
        for(int i = 0; i < inv.getContainerSize(); ++i) {
            ItemStack itemstack = inv.getItem(i);
            if (!itemstack.isEmpty() && itemstack.getItem() == item) {
                if(itemstack.getCount() >= count) {
                    inv.removeItem(i, count);
                    return;
                }
                else{
                    count -= itemstack.getCount();

                    inv.removeItem(i, itemstack.getCount());
                }
            }
        }
    }

    public static boolean hasAnyArmour(Player plr){
        return !plr.getItemBySlot(EquipmentSlot.HEAD).isEmpty() || !plr.getItemBySlot(EquipmentSlot.CHEST).isEmpty() ||
                !plr.getItemBySlot(EquipmentSlot.LEGS).isEmpty() || !plr.getItemBySlot(EquipmentSlot.FEET).isEmpty();
    }
}
