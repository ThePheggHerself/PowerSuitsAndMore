package phewitch.powersuits.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.client.event.RenderGuiEvent;

public interface IHUDItem {

    public void renderGUI(RenderGuiEvent event, PoseStack matrix);
}
