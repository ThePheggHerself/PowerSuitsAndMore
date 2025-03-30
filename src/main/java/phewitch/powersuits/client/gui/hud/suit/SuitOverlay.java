package phewitch.powersuits.client.gui.hud.suit;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import phewitch.powersuits.client.data.ClientData;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourChest;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourHelmet;

public class SuitOverlay {
    public static final IGuiOverlay HUD_Suit = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;
        var plr = Minecraft.getInstance().player;
        var helmet = SuitArmourHelmet.getHelmet(plr);

        if (helmet != null) {
            var chestplate = SuitArmourChest.getChestplate(plr);

            if(chestplate == null)
            {
                guiGraphics.drawString(gui.getFont(), "ERR_POWER_DISCONNECTED",
                        10, 10, -1);
            }
            else {
                guiGraphics.drawString(gui.getFont(), "Power: " + ClientData.suitPower + "/" + ClientData.maxSuitPower,
                        10, 10, -1);
            }
        }
    }));
}
