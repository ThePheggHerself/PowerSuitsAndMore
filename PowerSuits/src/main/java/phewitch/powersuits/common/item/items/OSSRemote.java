package phewitch.powersuits.common.item.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.client.gui.hud.oss.OSSMenu;
import phewitch.powersuits.common.OSS.OSSManager;

public class OSSRemote extends Item implements MenuProvider {
    public OSSRemote(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Component getDisplayName() { return Component.literal(OSSManager.OSSMenuDisplayName()); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pPlayer.isCrouching()){
            pPlayer.openMenu(new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer1) -> {
                return new OSSMenu(pContainerId, pPlayerInventory);
            }, Component.literal("Orbital Suit Storage")));

            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }

        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new OSSMenu(pContainerId, pPlayerInventory);
    }
}
