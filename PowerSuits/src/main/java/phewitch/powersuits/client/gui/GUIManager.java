package phewitch.powersuits.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.client.gui.hud.oss.OSSMenu;
import phewitch.powersuits.client.gui.hud.workbench.WorkbenchMenu;

import java.util.HashMap;
import java.util.Map;

public class GUIManager {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, PowerSuits.MODID);

    public static final RegistryObject<MenuType<OSSMenu>> OSS_MENU =
            MENUS.register("oss_menu", () -> new MenuType<OSSMenu>(OSSMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final RegistryObject<MenuType<WorkbenchMenu>> WORKBENCH_MENU =
            MENUS.register("workbench_menu", () -> IForgeMenuType.create(WorkbenchMenu::new));

    public static void register(IEventBus eventBus) { MENUS.register(eventBus); }

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
