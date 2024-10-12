package phewitch.powersuits.Common;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.openjdk.nashorn.internal.runtime.logging.Logger;
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
                sAB.handleFallDamage(ev);
            }
        }
    }

    @SubscribeEvent
    public void EntityHurtEvent(LivingHurtEvent ev){
        if(ev.getEntity() instanceof Player plr){
            if(plr.getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB){
                sAB.handleHurt(ev);
            }
        }
    }

    @SubscribeEvent
    public void TickEvent(TickEvent.PlayerTickEvent ev){
        if(ev.player.getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB){
            sAB.playerTickHandler(ev);
        }
    }
}
