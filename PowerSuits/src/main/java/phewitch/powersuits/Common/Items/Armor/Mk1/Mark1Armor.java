package phewitch.powersuits.Common.Items.Armor.Mk1;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.Suits;

import java.util.List;

public class Mark1Armor extends SuitArmourBase {
    public float flightTime = 50;
    public float maxFuel = 50;
    public Mark1Armor(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder, "mk1");
        this.fallDamageMultiplier = 0.6f;
        shootsLasers = false;
    }

    @Override
    public void onArmorTick(ItemStack item, Level level, Player player) {
        if(hasBoots(player) && item.getItem() == Suits.MK1_BOOTS.get()) {
            if (Minecraft.getInstance().options.keyJump.isDown() && this.flightTime > 0) {
                var motion = player.getDeltaMovement();
                var upwardsVelocity = motion.get(Direction.Axis.Y);
                upwardsVelocity += 0.05d;

                if (upwardsVelocity > 1)
                    upwardsVelocity = 1;

                player.setDeltaMovement(motion.get(Direction.Axis.X), upwardsVelocity, motion.get(Direction.Axis.Z));

                flightTime -= 1;
                if (flightTime < 0)
                    flightTime = 0;
            } else if (player.isOnGround() && flightTime < maxFuel) {
                flightTime += 2;

                if (flightTime > maxFuel)
                    flightTime = maxFuel;
            }
        }
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public void renderGUI(TickEvent.RenderTickEvent event, PoseStack matrix) {
        var player = minecraft.player;

        if (minecraft.isPaused() || player == null || player.level == null || minecraft.options.hideGui)
            return;

        if(hasBoots(player)) {

            var instance = Minecraft.getInstance();
            var window = Minecraft.getInstance().getWindow();

            int x = window.getGuiScaledWidth();
            int y = window.getGuiScaledHeight();
            int color = Integer.parseInt("33AA66", 16);

            instance.font.draw(matrix, "Fuel: " + flightTime, x - 75, y - 25, color);
        }
    }

    @Override
    public void appendHoverText(ItemStack item, @Nullable Level p_41422_, List<Component> components, TooltipFlag p_41424_) {
        components.add(new TranslatableComponent("tooltip.powersuits." + name + ".identifier"));

        components.add(new TranslatableComponent("tooltip.powersuits." + name + ".extra"));
    }
}
