package phewitch.powersuits.Common;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import phewitch.powersuits.Common.Entity.EntityManager;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.ItemManager;

public class CommonEvents {
    public static CommonEvents Instance;

    public CommonEvents(IEventBus eventBus){
        Instance = this;

        ItemManager.register(eventBus);
        EntityManager.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void PlayerFallEvent(LivingFallEvent ev){
        if(ev.getEntity() instanceof Player plr){
            if(plr.getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB){

                if(sAB.name == "mk1")
                    ev.setDistance(ev.getDistance() - 4);
                else if(sAB.name == "mk2")
                    ev.setDistance(ev.getDistance() - 9);

                ev.setDamageMultiplier(sAB.fallDamageMultiplier);
            }
        }
    }
}
