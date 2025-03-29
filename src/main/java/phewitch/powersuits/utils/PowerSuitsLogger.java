package phewitch.powersuits.utils;

import phewitch.powersuits.PowerSuits;

public class PowerSuitsLogger {

    // ANSI escape codes for standard colors.
    private static final String ANSI_RESET  = "\u001B[0m";
    private static final String ANSI_BLACK  = "\u001B[30m";
    private static final String ANSI_RED    = "\u001B[31m";
    private static final String ANSI_GREEN  = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE   = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN   = "\u001B[36m";
    private static final String ANSI_WHITE  = "\u001B[37m";

    /**
     * LogLevel enum defines preset labels and their associated ANSI color codes.
     */
    public enum LogLevel {
        INFO("INFO", ANSI_GREEN),
        WARN("WARN", ANSI_YELLOW),
        ERROR("ERROR", ANSI_RED),
        DEBUG("DEBUG", ANSI_CYAN);

        private final String label;
        private final String color;

        LogLevel(String label, String color) {
            this.label = label;
            this.color = color;
        }

        public String getLabel() {
            return this.label;
        }

        public String getColor() {
            return this.color;
        }
    }

    /**
     * Converts a string that uses '&' based color codes into a string with ANSI escape codes.
     *
     * @param text the text with '&' color codes.
     * @return the string with ANSI escape sequences.
     */
    public static String convertColorCodesToANSI(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&0", ANSI_BLACK)
                .replace("&1", ANSI_BLUE)
                .replace("&2", ANSI_GREEN)
                .replace("&3", ANSI_CYAN)
                .replace("&4", ANSI_RED)
                .replace("&5", ANSI_PURPLE)
                .replace("&6", ANSI_YELLOW)
                .replace("&7", ANSI_WHITE)
                .replace("&8", ANSI_BLACK)
                .replace("&9", ANSI_BLUE)
                .replace("&a", ANSI_GREEN)
                .replace("&b", ANSI_CYAN)
                .replace("&c", ANSI_RED)
                .replace("&d", ANSI_PURPLE)
                .replace("&e", ANSI_YELLOW)
                .replace("&f", ANSI_WHITE);
    }

    /**
     * Formats the log message by combining the global prefix, a colored level prefix,
     * and the actual message.
     *
     * @param level   the log level preset.
     * @param message the log message in '&' color code format.
     * @return the full formatted message with ANSI color codes.
     */
    private static String formatMessage(LogLevel level, String message) {
        String globalPrefixANSI = convertColorCodesToANSI(PowerSuits.GLOBAL_PREFIX.getString());
        String levelPrefix = level.getColor() + "[" + level.getLabel() + "]" + ANSI_RESET;
        String body = convertColorCodesToANSI(message);

        return globalPrefixANSI + " " + levelPrefix + " " + body + ANSI_RESET;
    }

    /**
     * Logs an informational message.
     *
     * @param message the message to log.
     */
    public static void info(String message) {
        PowerSuits.LOGGER.info(formatMessage(LogLevel.INFO, message));
    }

    /**
     * Logs a warning message.
     *
     * @param message the message to log.
     */
    public static void warn(String message) {
        PowerSuits.LOGGER.warn(formatMessage(LogLevel.WARN, message));
    }

    /**
     * Logs an error message.
     *
     * @param message the message to log.
     */
    public static void error(String message) {
        PowerSuits.LOGGER.error(formatMessage(LogLevel.ERROR, message));
    }

    /**
     * Logs a debug message.
     *
     * @param message the message to log.
     */
    public static void debug(String message) {
        PowerSuits.LOGGER.debug(formatMessage(LogLevel.DEBUG, message));
    }

}
