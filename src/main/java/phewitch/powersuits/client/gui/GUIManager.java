package phewitch.powersuits.client.gui;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.client.gui.hud.oss.OSSMenu;
import phewitch.powersuits.client.gui.hud.workbench.WorkbenchMenu;

public class GUIManager {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, PowerSuits.MODID);

    public static final RegistryObject<MenuType<OSSMenu>> OSS_MENU =
            MENUS.register("oss_menu", () -> new MenuType<OSSMenu>(OSSMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final RegistryObject<MenuType<WorkbenchMenu>> WORKBENCH_MENU =
            MENUS.register("workbench_menu", () -> IForgeMenuType.create(WorkbenchMenu::new));

    public static void register(IEventBus eventBus) { MENUS.register(eventBus); }
}
