package phewitch.powersuits.client.gui.hud.suit;

import ca.weblite.objc.Client;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import phewitch.powersuits.client.data.ClientData;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;

public class SuitOverlay {
    public static final IGuiOverlay HUD_Suit = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        if(ClientData.getSuitPower() < 0 || ClientData.getMaxSuitPower() < 0)
            return;
        else {
            var plr = Minecraft.getInstance().player;
            var sAB = SuitArmourBase.isWearingAnyPiece(plr);

            if(sAB != null) {
                guiGraphics.drawString(gui.getFont(), "Power: " + String.format("%.0f", sAB.features.currentPower) + "/" + String.format("%.0f", sAB.features.maxPower),
                        10, 10, -1);
            }
        }
    }));
}
