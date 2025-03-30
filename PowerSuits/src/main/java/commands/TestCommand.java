package commands;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import annotations.Command;
import phewitch.powersuits.common.OSS.OSSManager;
import phewitch.powersuits.common.entity.mobs.SuitSentry;
import phewitch.powersuits.common.item.suits.armorbase.SuitAbilitiesManager;

@Command(
        name = "powerhelp",
        description = "Displays all PowerSuit commands with their descriptions and arguments.",
        isPlayerOnly = true
)
public class TestCommand extends CommandFormat<CommandSourceStack> {


    @Override
    protected Class<?> getValidSourceClass() {
        return ServerPlayer.class;
    }

    @Override
    protected int executeCommand(CommandSourceStack source, String[] args) {
        sendHelpMessage(source);
        return 1;
    }

    private void sendHelpMessage(CommandSourceStack source) {
        MutableComponent header = Component.literal("=== PowerSuit Commands ===\n")
                .withStyle(ChatFormatting.GOLD);

        MutableComponent helpCommand = Component.literal("/powerhelp")
                .withStyle(ChatFormatting.AQUA);
        helpCommand.append(Component.literal(" - Displays this help menu")
                .withStyle(ChatFormatting.GREEN));
        helpCommand.append(Component.literal("\n"));

        MutableComponent activateCommand = Component.literal("/powersuit activate <suit>")
                .withStyle(ChatFormatting.AQUA);
        activateCommand.append(Component.literal(" - Activates the specified power suit")
                .withStyle(ChatFormatting.GREEN));
        activateCommand.append(Component.literal("\n"));

        MutableComponent statusCommand = Component.literal("/powersuit status [player]")
                .withStyle(ChatFormatting.AQUA);
        statusCommand.append(Component.literal(" - Shows the suit status for a player (defaults to self)")
                .withStyle(ChatFormatting.GREEN));
        statusCommand.append(Component.literal("\n"));

        MutableComponent footer = Component.literal("=== PowerSuit Commands ===\n")
                .withStyle(ChatFormatting.GOLD);


        MutableComponent message = header.copy();
        message.append(helpCommand);
        message.append(activateCommand);
        message.append(statusCommand);
        message.append(footer);

        source.sendSystemMessage(message);
    }
}