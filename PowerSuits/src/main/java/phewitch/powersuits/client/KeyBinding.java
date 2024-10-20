package phewitch.powersuits.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;
import phewitch.powersuits.PowerSuits;

public class KeyBinding {

    public static String KEY_CATEGORY = "key.category." + PowerSuits.MODID;
    static String ability1 = "key." + PowerSuits.MODID + ".suit.ability1";
    static String ability2 = "key." + PowerSuits.MODID + ".suit.ability2";
    static String ability3 = "key." + PowerSuits.MODID + ".suit.ability3";
    static String ability4 = "key." + PowerSuits.MODID + ".suit.ability4";

    public static final KeyMapping ABILITY_1 =
            new KeyMapping(ability1, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY);
    public static final KeyMapping ABILITY_2 =
            new KeyMapping(ability2, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, KEY_CATEGORY);
    public static final KeyMapping ABILITY_3 =
            new KeyMapping(ability3, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY);

    public static final KeyMapping ABILITY_4 =
            new KeyMapping(ability4, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY);
}
