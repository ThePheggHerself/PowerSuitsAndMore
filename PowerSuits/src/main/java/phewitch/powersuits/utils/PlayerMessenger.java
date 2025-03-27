package phewitch.powersuits.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import phewitch.powersuits.PowerSuits;

import java.util.Collection;

/**
 * Utility class for managing and sending messages to players in Minecraft.
 * This class provides methods to customize and send messages to clients or server players,
 * ensuring a consistent global prefix is used in the mod messaging service.
 */
public class PlayerMessenger {

    /**
     * This class is designed to provide static methods for messaging functionality
     * and should not be instantiated.
     */
    private PlayerMessenger() { }

    /**
     * Converts a string with '&' color codes into a Component with proper color formatting.
     *
     * @param text the text with '&' color codes.
     * @return a Component with color formatting applied.
     */
    public static Component colour(String text) {
        if(text == null)
            return Component.literal("");
        return Component.literal(text.replace("&", "§"));
    }


    /**
     * Formats a message by appending the provided message to a prefix.
     *
     * @param prefix The prefix to prepend to the message. If null, the global prefix will be used.
     * @param message The message to format and append to the prefix.
     * @return A formatted message as a {@code Component}, combining the specified or global prefix and the provided message.
     */
    private static Component formatMessage(Component prefix, String message) {
        Component chosenPrefix = (prefix != null) ? prefix : PowerSuits.GLOBAL_PREFIX;
        // Convert message text for color codes and append to prefix.
        return chosenPrefix.copy().append(colour(message));
    }

    /**
     * Sends a system message to the local client. If a custom prefix is supplied, it will be used;
     * otherwise, a global default prefix will be applied.
     *
     * @param customPrefix the custom prefix for the message. If null, the global prefix will be used.
     * @param message      the message to send to the client.
     */
    @OnlyIn(Dist.CLIENT)
    private static void sendSystemMessageToClient(Component customPrefix, String message) {
        var minecraft = Minecraft.getInstance();

        if (minecraft.level != null && minecraft.player != null) {
            var prefixToUse = (customPrefix != null) ? customPrefix : PowerSuits.GLOBAL_PREFIX;
            minecraft.player.sendSystemMessage(formatMessage(prefixToUse, message));
        }
    }

    /**
     * Overload: Sends a system message to the local client using a custom prefix provided as a String.
     * The method converts the prefix string using & color codes.
     *
     * @param customPrefix the custom prefix string to use (using '&' for colors).
     * @param message      the message to send.
     */
    @OnlyIn(Dist.CLIENT)
    public static void sendSystemMessageToClient(String customPrefix, String message) {
        Component prefixComponent = (customPrefix != null) ? colour(customPrefix) : PowerSuits.GLOBAL_PREFIX;
        sendSystemMessageToClient(prefixComponent, message);
    }

    /**
     * Overload: Sends a system message to the local client without a prefix argument
     * This will set the global prefix for default use.
     *
     * @param message      the message to send.
     */
    @OnlyIn(Dist.CLIENT)
    public static void sendSystemMessageToClient(String message) {
        sendSystemMessageToClient(PowerSuits.GLOBAL_PREFIX, message);
    }

    ///  --- Method Instance Checker --- ///

    /**
     * Sends a message to a player using the appropriate method depending on whether the player is on server or client side.
     * Use this instead
     * @param player       the target player.
     * @param customPrefix the custom prefix to use for the message (can include '&' color codes),
     *                     or null to use a default prefix.
     * @param message      the message text.
     */
    public static void sendPlayerMessage(Player player, String customPrefix, String message) {
        if (player instanceof ServerPlayer serverPlayer) {
            // Server side: Send the message to the specific server player.
            sendMessageToServerPlayer(serverPlayer, customPrefix, message);
        } else {
            // Client side: Send the message directly to the local client.
            sendSystemMessageToClient(customPrefix, message);
        }
    }

    /**
     * Sends a message to a player using the appropriate method depending on whether the player is on server or client side.
     * Use this instead
     *
     * @param player       the target player.
     *                     or null to use a default prefix.
     * @param message      the message text.
     */
    public static void sendPlayerMessage(Player player, String message) {
        if (player instanceof ServerPlayer serverPlayer) {
            // Server side: Send the message to the specific server player.
            sendMessageToServerPlayer(serverPlayer, PowerSuits.GLOBAL_PREFIX, message);
        } else {
            // Client side: Send the message directly to the local client.
            sendSystemMessageToClient(PowerSuits.GLOBAL_PREFIX, message);
        }
    }

    ///  --- Server Side Methods --- ///

    /**
     * Sends a system message to a server player using a custom prefix (Component) and message.
     *
     * @param serverPlayer the target server player.
     * @param customPrefix the custom prefix Component to use, or null to use the global prefix.
     * @param message      the message text (can include '&' for colors).
     */
    public static void sendMessageToServerPlayer(ServerPlayer serverPlayer, Component customPrefix, String message) {
        if (serverPlayer != null) {
            serverPlayer.sendSystemMessage(formatMessage(customPrefix, message));
        }
    }

    /**
     * Overload: Sends a system message to a server player using a custom prefix provided as a String.
     *
     * @param serverPlayer the target server player.
     * @param customPrefix the custom prefix string to use (with '&' for colors), or null for the global prefix.
     * @param message      the message text.
     */
    public static void sendMessageToServerPlayer(ServerPlayer serverPlayer, String customPrefix, String message) {
        Component prefixComponent = (customPrefix != null) ? colour(customPrefix) : PowerSuits.GLOBAL_PREFIX;
        sendMessageToServerPlayer(serverPlayer, prefixComponent, message);
    }


    public static void broadcastMessage(Component customPrefix, String message, Collection<ServerPlayer> players){
        Component formattedMessage = formatMessage(customPrefix, message);
        players.forEach(player -> player.sendSystemMessage(formattedMessage));
    }

    public static void broadcastMessage(String message, Collection<ServerPlayer> players) {
        broadcastMessage(PowerSuits.GLOBAL_PREFIX, message, players);
    }

}
