package honeyandhell.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BeeNestBlock extends HorizontalBlock
{
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(3.0D, 6.0D, 3.0D, 13.0D, 16.0D, 13.0D);

    public BeeNestBlock(Properties p_i48426_1_) {
        super(p_i48426_1_);
        this.setDefaultState(((BlockState)this.stateContainer.getBaseState()).with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public void onBlockClicked(BlockState p_196270_1_, World p_196270_2_, BlockPos p_196270_3_, PlayerEntity p_196270_4_)
    {
        if (p_196270_4_.getHeldItem(p_196270_4_.swingingHand).getItem() != Items.SHEARS) {
            this.blockFall(p_196270_2_, p_196270_3_);
        }
        else
        {
            super.onBlockClicked(p_196270_1_, p_196270_2_, p_196270_3_, p_196270_4_);
        }
    }

    private void blockFall(World p_176503_1_, BlockPos p_176503_2_)
    {
        if (p_176503_2_.getY() >= 0 && !p_176503_1_.isRemote)
        {
            FallingBlockEntity fallingblockentity = new FallingBlockEntity(p_176503_1_, (double) p_176503_2_.getX() + 0.5D, (double) p_176503_2_.getY(), (double) p_176503_2_.getZ() + 0.5D, p_176503_1_.getBlockState(p_176503_2_));
            this.onStartFalling(fallingblockentity);
            p_176503_1_.addEntity(fallingblockentity);
        }
    }

    protected void onStartFalling(FallingBlockEntity p_149829_1_) {
    }

    public static boolean canFallThrough(BlockState p_185759_0_) {
        Block block = p_185759_0_.getBlock();
        Material material = p_185759_0_.getMaterial();
        return p_185759_0_.isAir() || block == Blocks.FIRE || material.isLiquid() || material.isReplaceable();
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_)
    {
        return SHAPE;
    }

    @Override
    public boolean isValidPosition(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        Block block = p_196260_2_.getBlockState(p_196260_3_.up()).getBlock();
        return block.isIn(BlockTags.LEAVES);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockState blockstate = this.getDefaultState();
        IWorldReader iworldreader = p_196258_1_.getWorld();
        BlockPos blockpos = p_196258_1_.getPos();
        Direction[] var5 = p_196258_1_.getNearestLookingDirections();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            if (direction.getAxis().isHorizontal()) {
                blockstate = (BlockState)blockstate.with(HORIZONTAL_FACING, direction);
                if (blockstate.isValidPosition(iworldreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    @Override
    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (!p_196271_1_.isValidPosition(p_196271_4_, p_196271_5_)) {
            this.blockFall(p_196271_4_.getWorld(), p_196271_5_);
        }

        return super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{HORIZONTAL_FACING});
    }
}
