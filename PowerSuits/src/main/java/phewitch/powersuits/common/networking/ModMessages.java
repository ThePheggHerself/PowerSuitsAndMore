package phewitch.powersuits.common.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.networking.packets.client2server.*;
import phewitch.powersuits.common.networking.packets.server2client.S2CGetOSSSuits;

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

        net.messageBuilder(C2SSuitShootArrow.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSuitShootArrow::new)
                .encoder(C2SSuitShootArrow::toBytes)
                .consumerMainThread(C2SSuitShootArrow::handle)
                .add();

        net.messageBuilder(C2SSuitShootLaser.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSuitShootLaser::new)
                .encoder(C2SSuitShootLaser::toBytes)
                .consumerMainThread(C2SSuitShootLaser::handle)
                .add();

        net.messageBuilder(C2SSuitShootChestLaser.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSuitShootChestLaser::new)
                .encoder(C2SSuitShootChestLaser::toBytes)
                .consumerMainThread(C2SSuitShootChestLaser::handle)
                .add();

        net.messageBuilder(C2SSuitSentryMode.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSuitSentryMode::new)
                .encoder(C2SSuitSentryMode::toBytes)
                .consumerMainThread(C2SSuitSentryMode::handle)
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

        net.messageBuilder(S2CGetOSSSuits.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CGetOSSSuits::new)
                .encoder(S2CGetOSSSuits::toBytes)
                .consumerMainThread(S2CGetOSSSuits::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        Instance.sendToServer(message);
    }
    public static <MSG> void sendToClient(MSG message, ServerPlayer player){
        Instance.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
