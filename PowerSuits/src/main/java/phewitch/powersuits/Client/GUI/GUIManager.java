package phewitch.powersuits.Client.GUI;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import phewitch.powersuits.Common.Items.ItemManager;
import phewitch.powersuits.PowerSuits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
