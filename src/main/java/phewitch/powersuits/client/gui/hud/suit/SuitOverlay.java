package phewitch.powersuits.client.gui.hud.suit;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import phewitch.powersuits.client.data.ClientData;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourBase;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourChest;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourHelmet;

public class SuitOverlay {
    public static final IGuiOverlay HUD_Suit = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        var plr = Minecraft.getInstance().player;
        var helmet = SuitArmourHelmet.getHelmet(plr);

        if (helmet != null) {
            var chest = SuitArmourChest.getChestplate(plr);
            var legs = SuitArmourBase.hasLegs(plr);
            var boots = SuitArmourBase.hasBoots(plr);

            if(chest != null){
                guiGraphics.drawString(gui.getFont(), "Power: " + ClientData.suitPower + "/" + ClientData.maxSuitPower, 10, 10, -1);

                if(!legs)
                    guiGraphics.drawString(gui.getFont(), "WRN: ARMOR_COMPROMISED", 10, 20, -1);
                if(!boots)
                    guiGraphics.drawString(gui.getFont(), "WRN: FLIGHT_SYS_MALFUNCTION", 10, 30, -1);
            }
            else
                guiGraphics.drawString(gui.getFont(), "ERR: POWER_DISCONNECTED", 10, 10, -1);
        }
    }));
}
