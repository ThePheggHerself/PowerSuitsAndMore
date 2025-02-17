package phewitch.powersuits.client.gui.hud.workbench;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import phewitch.powersuits.client.gui.GUIManager;
import phewitch.powersuits.common.item.BlocksManager;
import phewitch.powersuits.common.item.blocks.workbench.WorkbenchBlockEntity;

public class WorkbenchMenu extends AbstractContainerMenu {
    public final WorkbenchBlockEntity entity;

    public WorkbenchMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public WorkbenchMenu(int pContainerId, Inventory inv, BlockEntity wbEntity) {
        this(pContainerId, inv, wbEntity, new SimpleContainerData(4));
    }

    public WorkbenchMenu(int pContainerId, Inventory inv, BlockEntity wbEntity, ContainerData data) {
        super(GUIManager.WORKBENCH_MENU.get(), pContainerId);
        checkContainerSize(inv, 4);
        this.entity = (WorkbenchBlockEntity) wbEntity;

        addPlayerInventory(inv);

        entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 249, 200));
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 267, 200));
            this.addSlot(new SlotItemHandler(iItemHandler, 2, 285, 200));
            this.addSlot(new SlotItemHandler(iItemHandler, 3, 303, 200));
        });
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(pPlayer.level(), entity.getBlockPos()),
                pPlayer, BlocksManager.WORKBENCH.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        int x = 70;
        int y = 142;

        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, x + l * 18, y + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, x + k * 18, 200));
        }
    }
}
