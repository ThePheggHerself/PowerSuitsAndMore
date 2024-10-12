package phewitch.powersuits.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;
import phewitch.powersuits.PowerSuits;

public class KeyBinding {

    public static String KEY_CATEGORY = "key.category." + PowerSuits.MODID;
    public static String KEY_SHOOT_ARROW = "key." + PowerSuits.MODID + ".suit.shootarrow";
    public static String KEY_SHOOT_LASER= "key." + PowerSuits.MODID + ".suit.shootlaser";
    public static String KEY_SHOOT_CHEST_LASER= "key." + PowerSuits.MODID + ".suit.shootchestlaser";

    public static final KeyMapping SHOOT_ARROW_KEY =
            new KeyMapping(KEY_SHOOT_ARROW, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY);
    public static final KeyMapping SHOOT_LASER_KEY =
            new KeyMapping(KEY_SHOOT_LASER, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, KEY_CATEGORY);
    public static final KeyMapping SHOOT_CHEST_LASER_KEY =
            new KeyMapping(KEY_SHOOT_CHEST_LASER, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY);
}
