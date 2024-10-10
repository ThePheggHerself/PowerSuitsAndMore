package phewitch.powersuits.Common.Items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.Armor.Suits;
import phewitch.powersuits.Common.Items.Materials.*;
import phewitch.powersuits.PowerSuits;

public class CreativeTabs {

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PowerSuits.MODID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("powersuits_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Palladium.PALLADIUM_POWER_CORE.get()))
                    .title(Component.translatable("itemGroup.powersuitstab"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(Titanium.STONE_TITANIUM_ORE.get());
                        pOutput.accept(Titanium.DEEPSLATE_TITANIUM_ORE.get());
                        pOutput.accept(Titanium.RAW_TITANIUM.get());
                        pOutput.accept(Titanium.TITANIUM_INGOT.get());
                        pOutput.accept(Titanium.TITANIUM_PLATE.get());

                        pOutput.accept(Titanium.TITANIUM_SWORD.get());
                        pOutput.accept(Titanium.TITANIUM_PICKAXE.get());
                        pOutput.accept(Titanium.TITANIUM_AXE.get());
                        pOutput.accept(Titanium.TITANIUM_SHOVEL.get());
                        pOutput.accept(Titanium.TITANIUM_HOE.get());
                        pOutput.accept(Titanium.TITANIUM_HELMET.get());
                        pOutput.accept(Titanium.TITANIUM_CHESTPLATE.get());
                        pOutput.accept(Titanium.TITANIUM_LEGGINGS.get());
                        pOutput.accept(Titanium.TITANIUM_BOOTS.get());



                        pOutput.accept(Palladium.STONE_PALLADIUM_ORE.get());
                        pOutput.accept(Palladium.DEEPSLATE_PALLADIUM_ORE.get());
                        pOutput.accept(Palladium.RAW_PALLADIUM.get());
                        pOutput.accept(Palladium.PALLADIUM_POWER_CORE.get());

                        pOutput.accept(Steel.STEEL_INGOT.get());
                        pOutput.accept(Steel.STEEL_PLATE.get());

                        pOutput.accept(Circuits.BASIC_CIRCUIT.get());
                        pOutput.accept(Circuits.ADVANCED_CIRCUIT.get());

                        pOutput.accept(Misc.MIXED_METAL_ALLOY.get());
                        pOutput.accept(Misc.MIXED_METAL_PLATE.get());
                        pOutput.accept(Misc.FOCUSING_CRYSTAL.get());
                        pOutput.accept(Misc.THRUSTER.get());





                        pOutput.accept(Suits.MK1_BOOTS.get());
                        pOutput.accept(Suits.MK1_LEGS.get());
                        pOutput.accept(Suits.MK1_CHEST.get());
                        pOutput.accept(Suits.MK1_HELM.get());

                        pOutput.accept(Suits.MK2_BOOTS.get());
                        pOutput.accept(Suits.MK2_LEGS.get());
                        pOutput.accept(Suits.MK2_CHEST.get());
                        pOutput.accept(Suits.MK2_HELM.get());

                        pOutput.accept(Suits.MK3_BOOTS.get());
                        pOutput.accept(Suits.MK3_LEGS.get());
                        pOutput.accept(Suits.MK3_CHEST.get());
                        pOutput.accept(Suits.MK3_HELM.get());

                        pOutput.accept(Suits.MK4_BOOTS.get());
                        pOutput.accept(Suits.MK4_LEGS.get());
                        pOutput.accept(Suits.MK4_CHEST.get());
                        pOutput.accept(Suits.MK4_HELM.get());




                    })
                    .build());

}
