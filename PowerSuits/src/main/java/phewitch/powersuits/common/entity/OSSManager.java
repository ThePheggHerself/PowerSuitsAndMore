package phewitch.powersuits.common.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.core.jmx.Server;
import phewitch.powersuits.common.entity.mobs.SuitSentry;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.client2server.C2SSummonOSSSuit;

import java.util.List;
import java.util.logging.Level;

public class OSSManager {
    private static List<String> suits;

    public static String getMenuDisplayName(){
        return "Orbital Suit Storage";
    }

    public static MutableComponent getOSSChatPrefix(){
        return Component.literal("§6[OSS] §7");
    }

    public static List<String> getSuits(){
        if(!Minecraft.getInstance().level.isClientSide)
            return null;

        return suits;
    }

    public static void setSuits(List<String> suitsList){
        if(!Minecraft.getInstance().level.isClientSide)
            return;

        suits = suitsList;
    }

    public static void addSuit(String name){
        if(!Minecraft.getInstance().level.isClientSide)
            return;

        if(!suits.contains(name))
            suits.add(name);
    }

    public static void removeSuit(String name){
        if(!Minecraft.getInstance().level.isClientSide)
            return;

        if(suits.contains(name))
            suits.remove(name);
    }

    public static void RequestSuitFromOSS(String suitName){
        if(!Minecraft.getInstance().level.isClientSide)
            return;

        removeSuit(suitName);
        Minecraft.getInstance().player.sendSystemMessage(OSSManager.getOSSChatPrefix()
                .append("Suit requested from Orbital Suit Storage"));

        ModMessages.sendToServer(new C2SSummonOSSSuit(suitName));
    }

    public static void ServerSummonSentry(Player owner, String suitName){
        //To ensure that this is only ever run on the server
        if(owner.level().isClientSide)
            return;

        var sentry = SpawnSentry(owner, suitName, 0, 50, 0);
        sentry.summonedFromOSS = true;
    }

    public static SuitSentry SpawnSentry(Player owner, String suitName){
        return SpawnSentry(owner, suitName, 0, 0.5f, 0);
    }

    public static SuitSentry SpawnSentry(Player owner, String suitName, double xOffset, double yOffset, double zOffset){
        if(owner.level().isClientSide)
            return null;

        var sentry = new SuitSentry(EntityManager.SENTRY.get(), owner.level(), owner, suitName);
        sentry.setPos(owner.getX() + xOffset, owner.getY() + yOffset, owner.getZ() + zOffset);
        sentry.setXRot(owner.getXRot());
        sentry.setYRot(owner.getYRot());
        owner.level().addFreshEntity(sentry);

        return sentry;
    }
}
