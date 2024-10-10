package phewitch.powersuits;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import phewitch.powersuits.Client.ClientEvents;
import phewitch.powersuits.Common.CommonEvents;
import phewitch.powersuits.Common.Items.CreativeTabs;
import phewitch.powersuits.Common.Items.Materials.Palladium;
import phewitch.powersuits.Common.networking.ModMessages;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("powersuits")
public class PowerSuits {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "powersuits";
    public static PowerSuits Instance;

    public static long lastTick = 0;

    public PowerSuits() {
        Instance = this;
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        new CommonEvents(eventBus);

        if(Dist.CLIENT.isClient()) {
            new ClientEvents(eventBus);
        }

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        CreativeTabs.register(eventBus);

        ModMessages.register();
    }
}
