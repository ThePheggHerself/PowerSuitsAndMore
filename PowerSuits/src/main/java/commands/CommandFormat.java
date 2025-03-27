package commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import annotations.Command;
import net.minecraft.server.level.ServerPlayer;

public abstract class CommandFormat extends BaseCommand {

    protected final String usage;
    protected final String[] aliases;
    protected final String permission;
    protected final boolean isPlayerOnly;

    public CommandFormat() {
        super();
        Command meta = this.getClass().getAnnotation(Command.class);
        if (meta == null) {
            throw new IllegalStateException("Command class " + this.getClass().getName() +
                    " must be annotated with @Command");
        }
        setName(meta.name());
        setDescription(meta.description());

        this.usage = meta.usage();
        this.aliases = meta.aliases();
        this.permission = meta.permission();
        this.isPlayerOnly = meta.isPlayerOnly();
    }

    /**
     * Override this method with the command logic.
     *
     * @param source the CommandSourceStack from which the command is executed and provides all sources
     * @param args   any additional arguments that have been passed along
     */
    protected abstract int executeCommand(ServerPlayer source, String[] args);

    /**
     * This was annoying to do but should work with any command
     * Going to change this to simply pass the player at some point
     *
     * @param dispatcher
     */
    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal(getName())
                        .executes(context -> {
                            CommandSourceStack source = context.getSource();
                            return executeCommand(source.getPlayer(), new String[0]);
                        })
                        .then(
                                Commands.argument("args", StringArgumentType.greedyString())
                                        .executes(context -> {
                                            CommandSourceStack source = context.getSource();
                                            String input = StringArgumentType.getString(context, "args");
                                            String[] splitArgs = input.trim().isEmpty() ? new String[0] : input.split("\\s+");
                                            return executeCommand(source.getPlayer(), splitArgs);
                                        })
                        )
        );
    }
}