package phewitch.powersuits.Common.Datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.Materials.Palladium;
import phewitch.powersuits.Common.Items.Materials.Titanium;
import phewitch.powersuits.PowerSuits;

public class BlockStateGenerator extends BlockStateProvider {
    public BlockStateGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PowerSuits.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(Titanium.STONE_TITANIUM_ORE);
        blockWithItem(Titanium.DEEPSLATE_TITANIUM_ORE);

        blockWithItem(Palladium.STONE_PALLADIUM_ORE);
        blockWithItem(Palladium.DEEPSLATE_PALLADIUM_ORE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
