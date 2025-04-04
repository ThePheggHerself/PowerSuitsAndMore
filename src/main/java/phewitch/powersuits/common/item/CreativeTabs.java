package phewitch.powersuits.common.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.item.suits.Suits;
import phewitch.powersuits.PowerSuits;

public class CreativeTabs {

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PowerSuits.MODID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("powersuits_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemsManager.PALLADIUM_POWER_CORE.get()))
                    .title(Component.translatable("itemGroup.powersuitstab"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(BlocksManager.STONE_TITANIUM_ORE.get());
                        pOutput.accept(BlocksManager.DEEPSLATE_TITANIUM_ORE.get());
                        pOutput.accept(BlocksManager.STONE_PALLADIUM_ORE.get());
                        pOutput.accept(BlocksManager.DEEPSLATE_PALLADIUM_ORE.get());

                        pOutput.accept(ItemsManager.RAW_TITANIUM.get());
                        pOutput.accept(ItemsManager.TITANIUM_INGOT.get());
                        pOutput.accept(ItemsManager.RAW_PALLADIUM.get());
                        pOutput.accept(ItemsManager.PALLADIUM_INGOT.get());
                        pOutput.accept(ItemsManager.STEEL_INGOT.get());
                        pOutput.accept(ItemsManager.MIXED_METAL_ALLOY.get());
                        pOutput.accept(ItemsManager.ADVANCED_METAL_ALLOY.get());
                        pOutput.accept(ItemsManager.TITANIUM_PLATE.get());
                        pOutput.accept(ItemsManager.STEEL_PLATE.get());
                        pOutput.accept(ItemsManager.MIXED_METAL_PLATE.get());
                        pOutput.accept(ItemsManager.ADVANCED_METAL_PLATE.get());

                        pOutput.accept(ItemsManager.BASIC_CIRCUIT.get());
                        pOutput.accept(ItemsManager.ADVANCED_CIRCUIT.get());
                        pOutput.accept(ItemsManager.PALLADIUM_POWER_CORE.get());
                        pOutput.accept(ItemsManager.ADVANCED_PALLADIUM_POWER_CORE.get());
                        pOutput.accept(ItemsManager.FOCUSING_CRYSTAL.get());
                        pOutput.accept(ItemsManager.PHOTONIC_BEAM_GENERATOR.get());
                        pOutput.accept(ItemsManager.THRUSTER.get());
                        pOutput.accept(ItemsManager.MICRO_CANNON.get());
                        pOutput.accept(ItemsManager.OSS_REMOTE.get());

                        pOutput.accept(ToolsManager.TITANIUM_SWORD.get());
                        pOutput.accept(ToolsManager.TITANIUM_PICKAXE.get());
                        pOutput.accept(ToolsManager.TITANIUM_AXE.get());
                        pOutput.accept(ToolsManager.TITANIUM_SHOVEL.get());
                        pOutput.accept(ToolsManager.TITANIUM_HOE.get());
                        pOutput.accept(ArmorManager.TITANIUM_HELMET.get());
                        pOutput.accept(ArmorManager.TITANIUM_CHESTPLATE.get());
                        pOutput.accept(ArmorManager.TITANIUM_LEGGINGS.get());
                        pOutput.accept(ArmorManager.TITANIUM_BOOTS.get());

                        pOutput.accept(ItemsManager.BASE_BOOTS.get());
                        pOutput.accept(ItemsManager.BASE_LEGGINGS.get());
                        pOutput.accept(ItemsManager.BASE_CHESTPLATE.get());
                        pOutput.accept(ItemsManager.BASE_HELMET.get());

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

                        pOutput.accept(Suits.MK5_BOOTS.get());
                        pOutput.accept(Suits.MK5_LEGS.get());
                        pOutput.accept(Suits.MK5_CHEST.get());
                        pOutput.accept(Suits.MK5_HELM.get());

                        pOutput.accept(Suits.MK5a_BOOTS.get());
                        pOutput.accept(Suits.MK5a_LEGS.get());
                        pOutput.accept(Suits.MK5a_CHEST.get());
                        pOutput.accept(Suits.MK5a_HELM.get());

                        pOutput.accept(Suits.MK5b_BOOTS.get());
                        pOutput.accept(Suits.MK5b_LEGS.get());
                        pOutput.accept(Suits.MK5b_CHEST.get());
                        pOutput.accept(Suits.MK5b_HELM.get());

                        pOutput.accept(Suits.MK5c_BOOTS.get());
                        pOutput.accept(Suits.MK5c_LEGS.get());
                        pOutput.accept(Suits.MK5c_CHEST.get());
                        pOutput.accept(Suits.MK5c_HELM.get());

                        pOutput.accept(Suits.MK5d_BOOTS.get());
                        pOutput.accept(Suits.MK5d_LEGS.get());
                        pOutput.accept(Suits.MK5d_CHEST.get());
                        pOutput.accept(Suits.MK5d_HELM.get());

                        pOutput.accept(Suits.MK6_BOOTS.get());
                        pOutput.accept(Suits.MK6_LEGS.get());
                        pOutput.accept(Suits.MK6_CHEST.get());
                        pOutput.accept(Suits.MK6_HELM.get());

                        pOutput.accept(Suits.MK7_BOOTS.get());
                        pOutput.accept(Suits.MK7_LEGS.get());
                        pOutput.accept(Suits.MK7_CHEST.get());
                        pOutput.accept(Suits.MK7_HELM.get());
                    })
                    .build());

}
