package phewitch.powersuits.Common.Items.Armor.Mk2;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import org.jetbrains.annotations.NotNull;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Renderer;
import phewitch.powersuits.Common.Items.Armor.Suits;

import java.util.function.Consumer;

public class Mark2Armor extends SuitArmourBase {
    public float flightTime = 300;
    public float maxFuel = 300;
    public Mark2Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mk2");
        this.fallDamageMultiplier = 0.3f;
        projectileDamage = 3;
        shootsLasers = false;
        shootsArrows = true;
    }

    @Override
    public void onArmorTick(ItemStack item, Level level, Player player) {
        if(hasBoots(player) && item.getItem() == Suits.MK2_BOOTS.get()) {
            if (Minecraft.getInstance().options.keyJump.isDown() && this.flightTime > 0) {
                var motion = player.getDeltaMovement();
                var upwardsVelocity = motion.get(Direction.Axis.Y);
                upwardsVelocity += 0.1d;

                if (upwardsVelocity > 1)
                    upwardsVelocity = 1;

                player.setDeltaMovement(motion.get(Direction.Axis.X), upwardsVelocity, motion.get(Direction.Axis.Z));

                flightTime -= 1;
                if (flightTime < 0)
                    flightTime = 0;
            } else if (player.onGround() && flightTime < maxFuel) {
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

        if (minecraft.isPaused() || player == null || player.level() == null || minecraft.options.hideGui)
            return;

        if(hasBoots(player)) {
            var instance = Minecraft.getInstance();
            var window = Minecraft.getInstance().getWindow();

            int x = window.getGuiScaledWidth();
            int y = window.getGuiScaledHeight();
            int color = Integer.parseInt("33AA66", 16);

            //instance.font.draw(matrix, "Fuel: " + flightTime, x - 75, y - 25, color);
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private Mark2Renderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if(this.renderer == null){
                    this.renderer = new Mark2Renderer();
                }

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }
}
