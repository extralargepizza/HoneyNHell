package honeyandhell.init;

import honeyandhell.common.util.inventory.ItemGroupHAH;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static honeyandhell.api.block.HAHBlocks.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        empty_honeycomb_block = registerBlock(new Block(Block.Properties.create(Material.ORGANIC, MaterialColor.SAND).hardnessAndResistance(0.1F)), "empty_honeycomb_block");
        honeycomb_block = registerBlock(new Block(Block.Properties.create(Material.ORGANIC, MaterialColor.SAND).hardnessAndResistance(0.1F)), "honeycomb_block");
        filled_honeycomb_block = registerBlock(new Block(Block.Properties.create(Material.ORGANIC, MaterialColor.SAND).hardnessAndResistance(0.1F)), "filled_honeycomb_block");
    }

    public static Block registerBlock(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(ItemGroupHAH.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

    public static Block registerBlock(Block block, BlockItem itemBlock, String name) {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);

        if (itemBlock != null) {
            itemBlock.setRegistryName(name);
            ForgeRegistries.ITEMS.register(itemBlock);
        }

        return block;
    }
}
