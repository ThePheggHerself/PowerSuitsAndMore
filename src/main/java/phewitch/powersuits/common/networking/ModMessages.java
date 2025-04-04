package phewitch.powersuits.common.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.networking.packets.client2server.*;
import phewitch.powersuits.common.networking.packets.server2client.S2CSuitEnergySync;
import phewitch.powersuits.common.networking.packets.server2client.S2CSyncOSS;

public class ModMessages {
    private static SimpleChannel Instance;
    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }
    
    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(
                new ResourceLocation(PowerSuits.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        Instance = net;

        net.messageBuilder(C2SSuitAbility.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSuitAbility::new)
                .encoder(C2SSuitAbility::toBytes)
                .consumerMainThread(C2SSuitAbility::handle)
                .add();

        net.messageBuilder(C2SSummonOSSSuit.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSummonOSSSuit::new)
                .encoder(C2SSummonOSSSuit::toBytes)
                .consumerMainThread(C2SSummonOSSSuit::handle)
                .add();

        net.messageBuilder(C2SGetOSSSuits.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SGetOSSSuits::new)
                .encoder(C2SGetOSSSuits::toBytes)
                .consumerMainThread(C2SGetOSSSuits::handle)
                .add();

        net.messageBuilder(C2SSuitQuickLaunch.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSuitQuickLaunch::new)
                .encoder(C2SSuitQuickLaunch::toBytes)
                .consumerMainThread(C2SSuitQuickLaunch::handle)
                .add();

        net.messageBuilder(S2CSyncOSS.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSyncOSS::new)
                .encoder(S2CSyncOSS::toBytes)
                .consumerMainThread(S2CSyncOSS::handle)
                .add();

        net.messageBuilder(S2CSuitEnergySync.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSuitEnergySync::new)
                .encoder(S2CSuitEnergySync::toBytes)
                .consumerMainThread(S2CSuitEnergySync::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        Instance.sendToServer(message);
    }
    public static <MSG> void sendToClient(MSG message, ServerPlayer player){
        Instance.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
