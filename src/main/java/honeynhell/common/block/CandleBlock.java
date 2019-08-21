package honeynhell.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
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

public class CandleBlock extends Block
{
    public static final IntegerProperty CANDLES;
    protected static final VoxelShape ONE_SHAPE;
    protected static final VoxelShape TWO_SHAPE;
    protected static final VoxelShape THREE_SHAPE;
    protected static final VoxelShape FOUR_SHAPE;

    public CandleBlock(Properties p_i48426_1_) {
        super(p_i48426_1_);
        this.setDefaultState(((BlockState)this.stateContainer.getBaseState()).with(CANDLES, 1));
    }

    @Override
    public int getLightValue(BlockState p_149750_1_) {
        return super.getLightValue(p_149750_1_) + 2 * (Integer)p_149750_1_.get(CANDLES);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockState lvt_2_1_ = p_196258_1_.getWorld().getBlockState(p_196258_1_.getPos());
        if (lvt_2_1_.getBlock() == this)
        {
            return (BlockState) lvt_2_1_.with(CANDLES, Math.min(4, (Integer) lvt_2_1_.get(CANDLES) + 1));
        }
        else
        {
            return (BlockState)super.getStateForPlacement(p_196258_1_);
        }
    }

    protected boolean isValidGround(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
        return !p_200014_1_.getCollisionShape(p_200014_2_, p_200014_3_).project(Direction.UP).isEmpty();
    }

    @Override
    public boolean isValidPosition(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos lvt_4_1_ = p_196260_3_.down();
        return this.isValidGround(p_196260_2_.getBlockState(lvt_4_1_), p_196260_2_, lvt_4_1_);
    }

    @Override
    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (!p_196271_1_.isValidPosition(p_196271_4_, p_196271_5_)) {
            return Blocks.AIR.getDefaultState();
        }

        return super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    @Override
    public boolean isReplaceable(BlockState p_196253_1_, BlockItemUseContext p_196253_2_) {
        return p_196253_2_.getItem().getItem() == this.asItem() && (Integer)p_196253_1_.get(CANDLES) < 4 ? true : super.isReplaceable(p_196253_1_, p_196253_2_);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch((Integer)p_220053_1_.get(CANDLES)) {
            case 1:
            default:
                return ONE_SHAPE;
            case 2:
                return TWO_SHAPE;
            case 3:
                return THREE_SHAPE;
            case 4:
                return FOUR_SHAPE;
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{CANDLES});
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_)
    {
        switch((Integer)p_180655_1_.get(CANDLES)) {
            case 1:
            default:
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.5D, (double)p_180655_3_.getY() + 0.6D, (double)p_180655_3_.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                break;
            case 2:
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.3D, (double)p_180655_3_.getY() + 0.6D, (double)p_180655_3_.getZ() + 0.3D, 0.0D, 0.0D, 0.0D);
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.6D, (double)p_180655_3_.getY() + 0.5D, (double)p_180655_3_.getZ() + 0.6D, 0.0D, 0.0D, 0.0D);
                break;
            case 3:
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.5D, (double)p_180655_3_.getY() + 0.6D, (double)p_180655_3_.getZ() + 0.7D, 0.0D, 0.0D, 0.0D);
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.6D, (double)p_180655_3_.getY() + 0.6D, (double)p_180655_3_.getZ() + 0.4D, 0.0D, 0.0D, 0.0D);
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.2D, (double)p_180655_3_.getY() + 0.5D, (double)p_180655_3_.getZ() + 0.3D, 0.0D, 0.0D, 0.0D);
                break;
            case 4:
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.7D, (double)p_180655_3_.getY() + 0.5D, (double)p_180655_3_.getZ() + 0.7D, 0.0D, 0.0D, 0.0D);
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.2D, (double)p_180655_3_.getY() + 0.7D, (double)p_180655_3_.getZ() + 0.6D, 0.0D, 0.0D, 0.0D);
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.7D, (double)p_180655_3_.getY() + 0.6D, (double)p_180655_3_.getZ() + 0.3D, 0.0D, 0.0D, 0.0D);
                p_180655_2_.addParticle(ParticleTypes.FLAME, (double)p_180655_3_.getX() + 0.2D, (double)p_180655_3_.getY() + 0.6D, (double)p_180655_3_.getZ() + 0.3D, 0.0D, 0.0D, 0.0D);
                break;
        }
    }

    static {
        CANDLES = IntegerProperty.create("candles", 1, 4);
        ONE_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
        TWO_SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
        THREE_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
        FOUR_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);
    }
}
