package phewitch.powersuits.client.gui.hud.suit;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import phewitch.powersuits.common.item.suits.armorbase.SuitAbilitiesManager;

public class SuitOverlay {
    public static final IGuiOverlay HUD_Suit = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;
        var plr = Minecraft.getInstance().player;
        var sAB = SuitAbilitiesManager.getSuitArmourBase(plr);

        if (sAB != null) {
            guiGraphics.drawString(gui.getFont(), "Power: " + String.format("%.0f", sAB.features.currentPower) + "/" + String.format("%.0f", sAB.features.maxPower),
                    10, 10, -1);
        }
    }));
}
