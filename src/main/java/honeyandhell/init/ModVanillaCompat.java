package honeyandhell.init;

import honeyandhell.api.block.HAHBlocks;
import honeyandhell.api.item.HAHItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class ModVanillaCompat
{
    public static void setup()
    {
        //Flammability
        registerFlammable(HAHBlocks.wasp_nest_block, 30, 60);
        registerFlammable(HAHBlocks.empty_honeycomb_block, 30, 60);
        registerFlammable(HAHBlocks.honeycomb_block, 30, 60);
        registerFlammable(HAHBlocks.filled_honeycomb_block, 30, 60);

        registerFlammable(HAHBlocks.polished_oak_wood, 5, 20);
        registerFlammable(HAHBlocks.polished_spruce_wood, 5, 20);
        registerFlammable(HAHBlocks.polished_birch_wood, 5, 20);
        registerFlammable(HAHBlocks.polished_jungle_wood, 5, 20);
        registerFlammable(HAHBlocks.polished_acacia_wood, 5, 20);
        registerFlammable(HAHBlocks.polished_dark_oak_wood, 5, 20);

        //Compostable
        registerCompostable(0.3F, HAHBlocks.bee_nest);
        registerCompostable(0.3F, HAHBlocks.wasp_nest_block);
        registerCompostable(0.3F, HAHBlocks.empty_honeycomb_block);
        registerCompostable(0.5F, HAHBlocks.honeycomb_block);
        registerCompostable(0.5F, HAHItems.honeycomb);
        registerCompostable(0.65F, HAHBlocks.filled_honeycomb_block);
        registerCompostable(0.65F, HAHItems.filled_honeycomb);
        registerCompostable(0.85F, HAHItems.honey_on_bread);

        //Mead Brewing Recipe
        BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.WATER)), Ingredient.fromStacks(new ItemStack(HAHItems.raw_honey)), new ItemStack(HAHItems.bottle_of_mead));
    }

    public static void registerFlammable(Block blockIn, int encouragement, int flammability)
    {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        fireblock.setFireInfo(blockIn, encouragement, flammability);
    }

    public static void registerCompostable(float chance, IItemProvider itemIn) {
        ComposterBlock.CHANCES.put(itemIn.asItem(), chance);
    }
}
