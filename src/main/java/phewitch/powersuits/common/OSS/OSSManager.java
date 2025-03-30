package phewitch.powersuits.common.OSS;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import phewitch.powersuits.client.data.ClientData;
import phewitch.powersuits.common.capabilities.Capabilities;
import phewitch.powersuits.common.entity.EntityManager;
import phewitch.powersuits.common.entity.mobs.SuitSentry;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.client2server.C2SSummonOSSSuit;
import phewitch.powersuits.common.networking.packets.server2client.S2CSyncOSS;

public class OSSManager {
    public static String OSSMenuDisplayName() {
        return "Orbital Suit Storage";
    }

    public static MutableComponent OSSChatPrefix() {
        return Component.literal("§6[OSS] §7");
    }

    public static void RequestSuitFromOSS(String suitName) {
        if (!Minecraft.getInstance().level.isClientSide)
            return;

        //PlayerMessenger.sendSystemMessageToClient(PowerSuits.OSS_PREFIX, "&e Suit requested from Orbital Suit Storage");

        ClientData.RemoveSuit(suitName);

        ModMessages.sendToServer(new C2SSummonOSSSuit(suitName));
    }

    public static void ServerSummonSentry(Player owner, String suitName) {
        //To ensure that this is only ever run on the server
        if (owner.level().isClientSide)
            return;

        var sentry = SpawnSentry(owner, suitName, 0, 50, 0);
        sentry.summonedFromOSS = true;
    }

    public static SuitSentry SpawnSentry(Player owner, String suitName) {
        return SpawnSentry(owner, suitName, 0, 0.5f, 0);
    }

    public static SuitSentry SpawnSentry(Player owner, String suitName, double xOffset, double yOffset, double zOffset) {
        if (owner.level().isClientSide)
            return null;

        var sentry = new SuitSentry(EntityManager.SENTRY.get(), owner.level(), owner, suitName);
        sentry.setPos(owner.getX() + xOffset, owner.getY() + yOffset, owner.getZ() + zOffset);
        sentry.setXRot(owner.getXRot());
        sentry.setYRot(owner.getYRot());
        owner.level().addFreshEntity(sentry);

        return sentry;
    }

    public static void AddSuitToPlayer(String SuitName, ServerPlayer Player) {
        Player.getCapability(Capabilities.PLAYER_OSS).ifPresent(playerOSS -> {
            if (!playerOSS.getSuits().contains(SuitName)) {
                playerOSS.addSuit(SuitName);
                ModMessages.sendToClient(new S2CSyncOSS(playerOSS.getSuits()), Player);
            }
        });
    }

    public static void RemoveSuitFromPlayer(String SuitName, ServerPlayer Player) {
        Player.getCapability(Capabilities.PLAYER_OSS).ifPresent(playerOSS -> {
            if (playerOSS.getSuits().contains(SuitName)) {
                playerOSS.removeSuit(SuitName);
                ModMessages.sendToClient(new S2CSyncOSS(playerOSS.getSuits()), Player);
            }
        });
    }
}
