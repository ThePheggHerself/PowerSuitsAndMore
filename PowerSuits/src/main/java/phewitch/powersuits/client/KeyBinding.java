package phewitch.powersuits.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;
import phewitch.powersuits.PowerSuits;

public class KeyBinding {

    public static String KEY_CATEGORY = "key.category." + PowerSuits.MODID;
    static String shootArrowKey = "key." + PowerSuits.MODID + ".suit.shootarrow";
    static String shootLaserKey = "key." + PowerSuits.MODID + ".suit.shootlaser";
    static String shootChestLaserKey = "key." + PowerSuits.MODID + ".suit.shootchestlaser";
    static String shootSentryKey = "key." + PowerSuits.MODID + ".suit.sentrymode";

    public static final KeyMapping SHOOT_ARROW_KEY =
            new KeyMapping(shootArrowKey, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY);
    public static final KeyMapping SHOOT_LASER_KEY =
            new KeyMapping(shootLaserKey, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, KEY_CATEGORY);
    public static final KeyMapping SHOOT_CHEST_LASER_KEY =
            new KeyMapping(shootChestLaserKey, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY);

    public static final KeyMapping SENTRY_MODE_KEY =
            new KeyMapping(shootSentryKey, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY);
}
