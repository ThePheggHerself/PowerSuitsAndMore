package commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import annotations.Command;
import phewitch.powersuits.utils.PowerSuitsLogger;

@Command(
        name = "basecommand",
        description = "A generic command using generics for source types."
)
public abstract class CommandFormat<T extends CommandSourceStack> extends BaseCommand {

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
     * Provide the valid class type for the command source.
     * For example, if this command is player-only, return ServerPlayer.class.
     * for non-player commands it will return the CommandSourceStack
     * You can use CommandSourceStack for more helper utils for example to determine the level of the Minecraft Instance
     */
    protected abstract Class<?> getValidSourceClass();

    /**
     * A single generic execute function call that will see the required type when used
     *
     * @param source the command source of type T (e.g., ServerPlayer)
     * @param args any additional arguments that have been passed along
     * @return a result code
     */
    protected abstract int executeCommand(T source, String[] args);

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal(getName())
                        .executes(context -> {
                            CommandSourceStack source = context.getSource();

                            if (isPlayerOnly && !getValidSourceClass().isInstance(source.getEntity())) {
                                PowerSuitsLogger.warn("&c This is a player only command.");
                                return 0;
                            }

                            @SuppressWarnings("unchecked")
                            T validSource = (T) source;
                            return executeCommand(validSource, new String[0]);
                        })
                        .then(
                                Commands.argument("args", StringArgumentType.greedyString())
                                        .executes(context -> {
                                            CommandSourceStack source = context.getSource();

                                            if (isPlayerOnly && !getValidSourceClass().isInstance(source.getEntity())) {
                                                PowerSuitsLogger.warn("&c This is a player only command.");
                                                return 0;
                                            }

                                            String input = StringArgumentType.getString(context, "args");
                                            String[] splitArgs = input.trim().isEmpty() ? new String[0] : input.split("\\s+");
                                            @SuppressWarnings("unchecked")
                                            T validSource = (T) source;
                                            return executeCommand(validSource, splitArgs);
                                        })
                        )
        );
    }
}