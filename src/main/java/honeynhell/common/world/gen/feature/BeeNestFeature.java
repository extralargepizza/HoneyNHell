package honeynhell.common.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import honeynhell.api.block.HAHBlocks;
import honeynhell.common.block.BeeNestBlock;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class BeeNestFeature extends Feature<NoFeatureConfig>
{
    public BeeNestFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(IWorld p_212245_1_, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig p_212245_5_)
    {
        int i = 0;

        for(int j = 0; j < 16; ++j)
        {
            BlockPos blockpos = p_212245_4_.add(p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8), p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8), p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8));
            if (p_212245_1_.getBlockState(blockpos).isAir(p_212245_1_, blockpos) && p_212245_1_.getBlockState(blockpos.down()).isAir(p_212245_1_, blockpos.down()) && p_212245_1_.getBlockState(blockpos.up()).getBlock().isIn(BlockTags.LEAVES) && !p_212245_1_.getBlockState(blockpos.up().north()).isAir(p_212245_1_, blockpos.up().north()) && !p_212245_1_.getBlockState(blockpos.up().south()).isAir(p_212245_1_, blockpos.up().south()) && !p_212245_1_.getBlockState(blockpos.up().east()).isAir(p_212245_1_, blockpos.up().east()) && !p_212245_1_.getBlockState(blockpos.up().west()).isAir(p_212245_1_, blockpos.up().west()))
            {
                p_212245_1_.setBlockState(blockpos, HAHBlocks.bee_nest.getDefaultState().with(BeeNestBlock.HORIZONTAL_FACING, Direction.byIndex(2 + p_212245_3_.nextInt(4))), 2);

                ++i;
            }
        }

        return i > 0;
    }
}