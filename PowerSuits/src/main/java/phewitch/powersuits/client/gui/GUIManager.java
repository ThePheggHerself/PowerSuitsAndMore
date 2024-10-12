package phewitch.powersuits.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.client.event.RenderGuiEvent;

import java.util.HashMap;
import java.util.Map;

public class GUIManager {
    private static final Map<String, IHUDItem> HudItems = new HashMap<String, IHUDItem>();

    public static void registerHUDItem(String name, IHUDItem item) {
        if(!HudItems.containsKey(name))
            HudItems.put(name, item);
    }

    public static void renderHUDItems(RenderGuiEvent event){
        PoseStack matrix = new PoseStack();

        for (var item : HudItems.entrySet()){
            item.getValue().renderGUI(event, matrix);
        }
    }
}
