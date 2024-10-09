package phewitch.powersuits.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import phewitch.powersuits.Client.GUI.GUIManager;
import phewitch.powersuits.Common.CommonCore;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.networking.ModMessages;
import phewitch.powersuits.Common.networking.Packets.C2SSuitShootArrow;
import phewitch.powersuits.PowerSuits;

public class ClientEvents {
    public static ClientEvents Instance;
    public ClientEvents(IEventBus eventBus){
        Instance = this;

        MinecraftForge.EVENT_BUS.register(this);
        ClientRegistry.registerKeyBinding(KeyBinding.SHOOT_ARROW_KEY);
    }

    @Mod.EventBusSubscriber(modid = PowerSuits.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent ev){
            var plr = Minecraft.getInstance().player;

            if(plr.getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB){
                if(sAB.shootsArrows && KeyBinding.SHOOT_ARROW_KEY.consumeClick()){
                    if(plr.getInventory().contains(new ItemStack(Items.ARROW))){
                        ModMessages.sendToServer(new C2SSuitShootArrow());
                    }
                    else {
                        plr.displayClientMessage(new TextComponent("No arrows detected"), true);
                    }
                }
            }
        }

        @SubscribeEvent
        public void onRenderGui(TickEvent.RenderTickEvent event){
            GUIManager.renderHUDItems(event);
        }
    }
}
