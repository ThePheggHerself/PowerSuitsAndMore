package phewitch.powersuits.common.item.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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
import phewitch.powersuits.common.capabilities.PlayerOSSProvider;
import phewitch.powersuits.common.entity.OSSManager;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.server2client.S2CGetOSSSuits;

public class OSSRemote extends Item implements MenuProvider {
    public OSSRemote(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Component getDisplayName() { return Component.literal(OSSManager.getMenuDisplayName()); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pLevel.isClientSide)
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));

        if(!pPlayer.isShiftKeyDown()){
            pPlayer.getCapability(PlayerOSSProvider.PLAYER_OSS).ifPresent(playerOSS -> {
                ModMessages.sendToClient(new S2CGetOSSSuits(playerOSS.getSuits()), (ServerPlayer)pPlayer);

                pPlayer.openMenu(new SimpleMenuProvider(((pContainerId, pPlayerInv, pPlayer1) -> {
                    return new OSSMenu(pContainerId, pPlayerInv);
                }), Component.literal("Orbital Suit Storage")));
            });

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
