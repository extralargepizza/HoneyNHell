package honeyandhell.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class BeeNestBlock extends FallingBlock {
    public static final DirectionProperty HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE;

    public BeeNestBlock(Properties p_i48426_1_) {
        super(p_i48426_1_);
        this.setDefaultState(((BlockState) this.stateContainer.getBaseState()).with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public int tickRate(IWorldReader p_149738_1_) {
        return 10;
    }

    @Override
    public void onBlockAdded(BlockState p_220082_1_, World p_220082_2_, BlockPos p_220082_3_, BlockState p_220082_4_, boolean p_220082_5_) {
    }

    @Override
    public void tick(BlockState p_196267_1_, World p_196267_2_, BlockPos p_196267_3_, Random p_196267_4_) {
    }

    @Override
    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return (BlockState) p_185499_1_.with(HORIZONTAL_FACING, p_185499_2_.rotate((Direction) p_185499_1_.get(HORIZONTAL_FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return p_185471_1_.rotate(p_185471_2_.toRotation((Direction) p_185471_1_.get(HORIZONTAL_FACING)));
    }

    @Override
    public void onBlockClicked(BlockState p_196270_1_, World p_196270_2_, BlockPos p_196270_3_, PlayerEntity p_196270_4_) {
        this.blockFall(p_196270_2_, p_196270_3_);
    }

    private void blockFall(World p_176503_1_, BlockPos p_176503_2_) {
        if (p_176503_2_.getY() >= 0 && !p_176503_1_.isRemote) {
            FallingBlockEntity fallingblockentity = new FallingBlockEntity(p_176503_1_, (double) p_176503_2_.getX() + 0.5D, (double) p_176503_2_.getY(), (double) p_176503_2_.getZ() + 0.5D, p_176503_1_.getBlockState(p_176503_2_));
            this.onStartFalling(fallingblockentity);
            p_176503_1_.addEntity(fallingblockentity);
        }
    }

    @Override
    protected void onStartFalling(FallingBlockEntity p_149829_1_) {
    }

    @Override
    public void onEndFalling(World p_176502_1_, BlockPos p_176502_2_, BlockState p_176502_3_, BlockState p_176502_4_)
    {
        p_176502_1_.setBlockState(p_176502_2_, Blocks.AIR.getDefaultState(), 2);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
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
        if (!this.isValidPosition(p_196271_1_,p_196271_4_, p_196271_5_)) {
            this.blockFall(p_196271_4_.getWorld(), p_196271_5_);
        }

        return super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{HORIZONTAL_FACING});
    }

    static {
        HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
        SHAPE = Block.makeCuboidShape(3.0D, 6.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    }
}