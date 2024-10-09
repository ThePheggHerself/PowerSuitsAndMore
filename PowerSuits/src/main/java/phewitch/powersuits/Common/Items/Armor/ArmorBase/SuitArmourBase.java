package phewitch.powersuits.Common.Items.Armor.ArmorBase;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.Client.GUI.GUIManager;
import phewitch.powersuits.Client.GUI.IHUDItem;
import phewitch.powersuits.Client.KeyBinding;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Armor;
import phewitch.powersuits.Common.Items.Armor.Suits;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

import java.util.List;

public class SuitArmourBase extends GeoArmorItem implements IAnimatable, IHUDItem {

    public String name = "BLANK NAME";
    //The fall damage multiplier. 0 = no damage, 1 = 100% damage, 2 = 200% damage
    public float fallDamageMultiplier = 0.1f;
    public boolean shootsArrows = false;
    public boolean shootsLasers = true;
    public int projectileDamage = 7;


    public final Minecraft minecraft = Minecraft.getInstance();
    public SuitArmourBase(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder, String name) {
        super(materialIn, slot, builder);

        this.name = name;

        GUIManager.registerHUDItem(name + "_armor", this);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<SuitArmourBase>(this, "controller",
                20, this::predicate));
    }
    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    private AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
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
}
