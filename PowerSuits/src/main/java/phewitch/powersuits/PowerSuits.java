package phewitch.powersuits;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import phewitch.powersuits.Client.ClientEvents;
import phewitch.powersuits.Common.CommonEvents;
import phewitch.powersuits.Common.Items.ItemManager;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("powersuits")
public class PowerSuits {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "powersuits";
    public static PowerSuits Instance;

    public static long lastTick = 0;

    public static CreativeModeTab CreativeTab = new CreativeModeTab("powersuitstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND_CHESTPLATE);
        }
    };

    public PowerSuits() {
        Instance = this;
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        new CommonEvents(eventBus);

        if(Dist.CLIENT.isClient())
            new ClientEvents(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
