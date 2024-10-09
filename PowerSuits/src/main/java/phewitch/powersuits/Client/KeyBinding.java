package phewitch.powersuits.Client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;
import phewitch.powersuits.PowerSuits;

public class KeyBinding {

    public static String KEY_CATEGORY = "key.category." + PowerSuits.MODID;
    public static String KEY_SHOOT_ARROW = "key." + PowerSuits.MODID + ".suit.shootarrow";

    public static final KeyMapping SHOOT_ARROW_KEY = new KeyMapping(KEY_SHOOT_ARROW, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY);
}
