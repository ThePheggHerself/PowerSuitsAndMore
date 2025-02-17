package phewitch.powersuits.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.item.BlocksManager;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.util.ModBlock;

public class BlockStateGenerator extends BlockStateProvider {
    public BlockStateGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PowerSuits.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(BlocksManager.STONE_TITANIUM_ORE);
        blockWithItem(BlocksManager.DEEPSLATE_TITANIUM_ORE);
        blockWithItem(BlocksManager.STONE_PALLADIUM_ORE);
        blockWithItem(BlocksManager.DEEPSLATE_PALLADIUM_ORE);
        //blockWithItem(BlocksManager.WORKBENCH);

        simpleBlock(BlocksManager.WORKBENCH.get(), new ModelFile.UncheckedModelFile(modLoc("block/workbench")));
    }

    private void blockWithItem(RegistryObject<ModBlock> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
