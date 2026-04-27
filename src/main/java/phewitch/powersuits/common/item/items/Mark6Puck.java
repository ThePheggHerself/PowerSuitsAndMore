package phewitch.powersuits.common.item.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.CommonCore;
import phewitch.powersuits.common.item.suits.Suits;
import phewitch.powersuits.utils.PlayerMessenger;

public class Mark6Puck extends Item {
    public Mark6Puck(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(CommonCore.hasAnyArmour(pPlayer)){
            PlayerMessenger.sendPlayerMessage(pPlayer, "&6[MARK6]&7", "&e The puck cannot be activated at this time");
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        }
        else {
            pPlayer.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Suits.MK6_HELM.get()));
            pPlayer.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Suits.MK6_CHEST.get()));
            pPlayer.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Suits.MK6_LEGS.get()));
            pPlayer.setItemSlot(EquipmentSlot.FEET, new ItemStack(Suits.MK6_BOOTS.get()));

            CommonCore.RemoveItemFromInventory(pPlayer, pPlayer.getItemInHand(pUsedHand).getItem(), 1);
            return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
        }
    }
}
