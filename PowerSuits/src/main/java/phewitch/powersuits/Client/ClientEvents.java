package phewitch.powersuits.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import phewitch.powersuits.Client.GUI.GUIManager;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.networking.ModMessages;
import phewitch.powersuits.Common.networking.Packets.C2SSuitShootArrow;
import phewitch.powersuits.PowerSuits;

public class ClientEvents {
    public static ClientEvents Instance;
    public ClientEvents(IEventBus eventBus){
        Instance = this;

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = PowerSuits.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{

        @SubscribeEvent
        public static void ClientSetup(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.SHOOT_ARROW_KEY);
        }


        static Minecraft instance;
        static LocalPlayer player;

        @SubscribeEvent
        public static void ClientTickEvent(TickEvent.ClientTickEvent ev){
            if(instance == null)
                instance = Minecraft.getInstance();
            if(player == null)
                player = instance.player;

            if (instance == null || instance.isPaused() || player == null || player.level() == null)
                return;

            if(player.getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB){
                if(sAB.shootsArrows && KeyBinding.SHOOT_ARROW_KEY.consumeClick()){
                    if(player.getInventory().contains(new ItemStack(Items.ARROW))){
                        ModMessages.sendToServer(new C2SSuitShootArrow());
                    }
                    else {
                        player.displayClientMessage(Component.translatable ("msg." + PowerSuits.MODID + ".suit.noarrows"), true);
                    }
                }
            }
        }

        @SubscribeEvent
        public void onRenderGui(RenderGuiEvent event){
            GUIManager.renderHUDItems(event);
        }
    }
}
