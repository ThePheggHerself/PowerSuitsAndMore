package phewitch.powersuits.utils;

import com.mojang.brigadier.CommandDispatcher;
import commands.BaseCommand;
import net.minecraft.commands.CommandSourceStack;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private static final List<BaseCommand> COMMANDS = new ArrayList<>();

    public static void registerCommand(BaseCommand command) {
        COMMANDS.add(command);
    }

    public static void registerALL(CommandDispatcher<CommandSourceStack> dispatcher) {
        for (BaseCommand command : COMMANDS) {
            command.register(dispatcher);
        }
    }
}
