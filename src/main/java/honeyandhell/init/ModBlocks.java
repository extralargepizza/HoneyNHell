package honeyandhell.init;

import honeyandhell.common.block.BeeNestBlock;
import honeyandhell.common.block.BeehiveBlock;
import honeyandhell.common.block.CandleBlock;
import honeyandhell.common.block.CrystallizedHoneyBlock;
import honeyandhell.common.util.inventory.ItemGroupHAH;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
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
        bee_nest = registerBlock(new BeeNestBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.SAND).hardnessAndResistance(3.0F, 9.0F).sound(SoundType.PLANT)), "bee_nest");

        wasp_nest_block = registerBlock(new Block(Block.Properties.create(Material.ORGANIC, MaterialColor.SAND).hardnessAndResistance(0.1F).sound(SoundType.PLANT)), "wasp_nest_block");
        empty_honeycomb_block = registerBlock(new Block(Block.Properties.create(Material.ORGANIC, MaterialColor.SAND).hardnessAndResistance(0.1F).sound(SoundType.CROP)), "empty_honeycomb_block");
        honeycomb_block = registerBlock(new Block(Block.Properties.create(Material.ORGANIC, MaterialColor.SAND).hardnessAndResistance(0.1F).sound(SoundType.CROP)), "honeycomb_block");
        filled_honeycomb_block = registerBlock(new Block(Block.Properties.create(Material.ORGANIC, MaterialColor.SAND).hardnessAndResistance(0.1F).sound(SoundType.CROP)), "filled_honeycomb_block");
        crystallized_honey = registerBlock(new CrystallizedHoneyBlock(Block.Properties.create(Material.GLASS, MaterialColor.YELLOW).hardnessAndResistance(0.3F).sound(SoundType.GLASS)), "crystallized_honey");

        beehive = registerBlock(new BeehiveBlock(Block.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "beehive");

        polished_oak_wood = registerBlock(new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_PLANKS)), "polished_oak_wood");
        polished_spruce_wood = registerBlock(new RotatedPillarBlock(Block.Properties.from(Blocks.SPRUCE_PLANKS)), "polished_spruce_wood");
        polished_birch_wood = registerBlock(new RotatedPillarBlock(Block.Properties.from(Blocks.BIRCH_PLANKS)), "polished_birch_wood");
        polished_jungle_wood = registerBlock(new RotatedPillarBlock(Block.Properties.from(Blocks.JUNGLE_PLANKS)), "polished_jungle_wood");
        polished_acacia_wood = registerBlock(new RotatedPillarBlock(Block.Properties.from(Blocks.ACACIA_PLANKS)), "polished_acacia_wood");
        polished_dark_oak_wood = registerBlock(new RotatedPillarBlock(Block.Properties.from(Blocks.DARK_OAK_PLANKS)), "polished_dark_oak_wood");

        white_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.SNOW).lightValue(6).hardnessAndResistance(0.1F)), "white_candle");
        orange_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.ADOBE).lightValue(6).hardnessAndResistance(0.1F)), "orange_candle");
        magenta_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.MAGENTA).lightValue(6).hardnessAndResistance(0.1F)), "magenta_candle");
        light_blue_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.LIGHT_BLUE).lightValue(6).hardnessAndResistance(0.1F)), "light_blue_candle");
        yellow_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.YELLOW).lightValue(6).hardnessAndResistance(0.1F)), "yellow_candle");
        lime_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.LIME).lightValue(6).hardnessAndResistance(0.1F)), "lime_candle");
        pink_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.PINK).lightValue(6).hardnessAndResistance(0.1F)), "pink_candle");
        gray_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.GRAY).lightValue(6).hardnessAndResistance(0.1F)), "gray_candle");
        light_gray_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.LIGHT_GRAY).lightValue(6).hardnessAndResistance(0.1F)), "light_gray_candle");
        cyan_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.CYAN).lightValue(6).hardnessAndResistance(0.1F)), "cyan_candle");
        purple_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.PURPLE).lightValue(6).hardnessAndResistance(0.1F)), "purple_candle");
        blue_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.BLUE).lightValue(6).hardnessAndResistance(0.1F)), "blue_candle");
        brown_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.BROWN).lightValue(6).hardnessAndResistance(0.1F)), "brown_candle");
        green_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.GREEN).lightValue(6).hardnessAndResistance(0.1F)), "green_candle");
        red_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.RED).lightValue(6).hardnessAndResistance(0.1F)), "red_candle");
        black_candle = registerBlock(new CandleBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.BLACK).lightValue(6).hardnessAndResistance(0.1F)), "black_candle");
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
