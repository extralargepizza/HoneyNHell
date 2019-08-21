package honeynhell.init;

import honeynhell.api.block.HNHBlocks;
import honeynhell.api.item.HNHItems;
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
import net.minecraftforge.fml.ModList;

public class ModVanillaCompat
{
    public static void setup()
    {
        //Flammability
        registerFlammable(HNHBlocks.wasp_nest_block, 30, 60);
        registerFlammable(HNHBlocks.empty_honeycomb_block, 30, 60);
        registerFlammable(HNHBlocks.honeycomb_block, 30, 60);
        registerFlammable(HNHBlocks.filled_honeycomb_block, 30, 60);

        registerFlammable(HNHBlocks.polished_oak_wood, 5, 20);
        registerFlammable(HNHBlocks.polished_spruce_wood, 5, 20);
        registerFlammable(HNHBlocks.polished_birch_wood, 5, 20);
        registerFlammable(HNHBlocks.polished_jungle_wood, 5, 20);
        registerFlammable(HNHBlocks.polished_acacia_wood, 5, 20);
        registerFlammable(HNHBlocks.polished_dark_oak_wood, 5, 20);

        if (ModList.get().isLoaded("biomesoplenty"))
        {
            registerFlammable(HNHBlocks.polished_fir_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_redwood_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_cherry_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_mahogany_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_jacaranda_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_palm_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_willow_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_dead_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_magic_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_umbran_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_hellbark_wood, 5, 20);
            registerFlammable(HNHBlocks.polished_ethereal_wood, 5, 20);
        }

        //Compostable
        registerCompostable(0.3F, HNHBlocks.bee_nest);
        registerCompostable(0.3F, HNHBlocks.wasp_nest_block);
        registerCompostable(0.3F, HNHBlocks.empty_honeycomb_block);
        registerCompostable(0.5F, HNHBlocks.honeycomb_block);
        registerCompostable(0.5F, HNHItems.honeycomb);
        registerCompostable(0.65F, HNHBlocks.filled_honeycomb_block);
        registerCompostable(0.65F, HNHItems.filled_honeycomb);
        registerCompostable(0.85F, HNHItems.honey_on_bread);

        //Mead Brewing Recipe
        BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.WATER)), Ingredient.fromStacks(new ItemStack(HNHItems.raw_honey)), new ItemStack(HNHItems.bottle_of_mead));
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
