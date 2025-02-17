package phewitch.powersuits.common.item;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.item.blocks.workbench.WorkbenchBlockEntity;

public class BlockEntityManager {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PowerSuits.MODID);

    public static final RegistryObject<BlockEntityType<WorkbenchBlockEntity>> WORKBENCH_ENTITY =
            BLOCK_ENTITIES.register("workbench_be", () ->
                    BlockEntityType.Builder.of(WorkbenchBlockEntity::new,
                            BlocksManager.WORKBENCH.get()).build(null));
}
