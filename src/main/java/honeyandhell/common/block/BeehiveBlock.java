package honeyandhell.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class BeehiveBlock extends Block
{
    protected static final VoxelShape SHAPE;
    protected static final VoxelShape SHAPE_BODY;
    protected static final VoxelShape SHAPE_TOP;

    public BeehiveBlock(Properties p_i48426_1_) {
        super(p_i48426_1_);
    }

    @Override
    public boolean isValidPosition(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        Block block = p_196260_2_.getBlockState(p_196260_3_.down()).getBlock();
        return block.isIn(BlockTags.DIRT_LIKE);
    }

    @Override
    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (!this.isValidPosition(p_196271_1_, p_196271_4_, p_196271_5_)) {
            return Blocks.AIR.getDefaultState();
        }

        return super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_)
    {
        return SHAPE;
    }

    static {
        SHAPE_BODY = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
        SHAPE_TOP = Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        SHAPE = VoxelShapes.or(SHAPE_BODY, SHAPE_TOP);
    }
}
