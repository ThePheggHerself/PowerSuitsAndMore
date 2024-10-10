package phewitch.powersuits.Client.GUI;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.event.TickEvent;

public interface IHUDItem {

    public void renderGUI(TickEvent.RenderTickEvent event, PoseStack matrix);
}
