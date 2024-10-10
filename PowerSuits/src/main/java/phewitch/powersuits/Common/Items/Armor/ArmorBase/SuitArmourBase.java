package phewitch.powersuits.Common.Items.Armor.ArmorBase;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.Client.GUI.GUIManager;
import phewitch.powersuits.Client.GUI.IHUDItem;
import phewitch.powersuits.Client.KeyBinding;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Armor;
import phewitch.powersuits.Common.Items.Armor.Suits;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import java.util.List;
import java.util.function.Consumer;

public class SuitArmourBase extends ArmorItem implements GeoItem, IHUDItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public String name = "BLANK NAME";
    //The fall damage multiplier. 0 = no damage, 1 = 100% damage, 2 = 200% damage
    public float fallDamageMultiplier = 0.1f;
    public boolean shootsArrows = false;
    public boolean shootsLasers = true;
    public int projectileDamage = 7;


    public final Minecraft minecraft = Minecraft.getInstance();
    public SuitArmourBase(ArmorMaterial materialIn, Type type, Properties properties, String name ) {
        super(materialIn, type, properties);

        this.name = name;

        GUIManager.registerHUDItem(name + "_armor", this);
    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<SuitArmourBase>(this, "controller",
                20, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public Boolean hasBoots(Player player){
        if(player == null)
            return false;

        return (player.getInventory().getArmor(0).getItem() instanceof ArmorItem ai) && ai.getMaterial() == material;
    }

    public Boolean hasLegs(Player player){
        if(player == null)
            return false;

        return (player.getInventory().getArmor(1).getItem() instanceof ArmorItem ai) && ai.getMaterial() == material;
    }

    public Boolean hasChestplate(Player player){
        if(player == null)
            return false;

        return (player.getInventory().getArmor(2).getItem() instanceof ArmorItem ai) && ai.getMaterial() == material;
    }

    public Boolean hasHelmet(Player player){
        if(player == null)
            return false;

        return (player.getInventory().getArmor(3).getItem() instanceof ArmorItem ai) && ai.getMaterial() == material;
    }

    public Boolean hasFullSet(Player player){
        if(player == null)
            return false;

        return hasBoots(player) && hasLegs(player) && hasChestplate(player) && hasHelmet(player);
    }

    public boolean hasBootsOrChestplate(Player player){
        return hasBoots(player) || hasChestplate(player);
    }

    @Override
    public void renderGUI(TickEvent.RenderTickEvent event, PoseStack matrix) {

    }

    @Override
    public void appendHoverText(ItemStack item, @Nullable Level p_41422_, List<Component> components, TooltipFlag p_41424_) {
        components.add(Component.translatable("tooltip.powersuits." + name + ".identifier"));

        components.add(Component.translatable("tooltip.powersuits." + name + ".extra"));
    }
}
