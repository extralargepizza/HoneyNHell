package honeynhell.common.block;

import honeynhell.api.block.HNHBlocks;
import honeynhell.api.item.HNHItems;
import net.minecraft.block.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
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
    public static final IntegerProperty OCCUPANCY;
    protected static final VoxelShape SHAPE;

    public BeeNestBlock(Properties p_i48426_1_) {
        super(p_i48426_1_);
        this.setDefaultState(((BlockState) this.stateContainer.getBaseState()).with(HORIZONTAL_FACING, Direction.NORTH).with(OCCUPANCY, 8));
    }

    @Override
    public boolean onBlockActivated(BlockState p_220051_1_, World p_220051_2_, BlockPos p_220051_3_, PlayerEntity p_220051_4_, Hand p_220051_5_, BlockRayTraceResult p_220051_6_) {
        ItemStack lvt_7_1_ = p_220051_4_.getHeldItem(p_220051_5_);
        if (lvt_7_1_.getItem() == HNHItems.net) {
            if (!p_220051_2_.isRemote) {
                if (p_220051_2_.getBlockState(p_220051_3_).get(OCCUPANCY) >= 8) {
                    //p_220051_2_.playSound((PlayerEntity)null, p_220051_3_, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    p_220051_2_.setBlockState(p_220051_3_, p_220051_2_.getBlockState(p_220051_3_).with(OCCUPANCY, 0), 11);

                    Item bee_item = HNHItems.bee_larva;
                    if (!p_220051_4_.inventory.addItemStackToInventory(new ItemStack(bee_item))) {
                        p_220051_4_.dropItem(new ItemStack(bee_item), false);
                    }
                }

                this.blockFall(p_220051_2_, p_220051_3_);
                lvt_7_1_.damageItem(1, p_220051_4_, (p_220282_1_) -> {
                    p_220282_1_.sendBreakAnimation(p_220051_5_);
                });
            }

            return true;
        }
        else if (lvt_7_1_.getItem() == HNHItems.bee_larva || lvt_7_1_.getItem() == HNHItems.worker_bee || lvt_7_1_.getItem() == HNHItems.drone_bee || lvt_7_1_.getItem() == HNHItems.queen_bee)
        {
            if (!p_220051_2_.isRemote) {
                int occupancy = p_220051_1_.get(OCCUPANCY);
                //p_220051_2_.playSound((PlayerEntity)null, p_220051_3_, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                p_220051_2_.setBlockState(p_220051_3_, p_220051_2_.getBlockState(p_220051_3_).with(OCCUPANCY, MathHelper.clamp(occupancy + 1, 0, 8)), 11);

                if (!p_220051_4_.abilities.isCreativeMode) {
                    lvt_7_1_.shrink(1);
                }
            }

            return true;
        }
        else
        {
            return super.onBlockActivated(p_220051_1_, p_220051_2_, p_220051_3_, p_220051_4_, p_220051_5_, p_220051_6_);
        }
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
        if (p_196270_1_.getBlock() == HNHBlocks.bee_nest)
        {
            p_196270_2_.setBlockState(p_196270_3_, p_196270_1_.with(OCCUPANCY, 0), 2);
            this.blockFall(p_196270_2_, p_196270_3_);
        }
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
        ItemEntity lvt_10_1_ = new ItemEntity(p_176502_1_, (double) p_176502_2_.getX() + 0.5D, (double) p_176502_2_.getY() + 0.1D, (double) p_176502_2_.getZ() + 0.5D, new ItemStack(HNHBlocks.bee_nest.getDefaultState().with(OCCUPANCY, 0).getBlock(), 1));
        lvt_10_1_.setMotion(0.05D * p_176502_1_.rand.nextDouble() * 0.02D, 0.05D, 0.05D * p_176502_1_.rand.nextDouble() * 0.02D);
        p_176502_1_.addEntity(lvt_10_1_);
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
                blockstate = (BlockState)blockstate.with(HORIZONTAL_FACING, direction).with(OCCUPANCY, 0);
                if (blockstate.isValidPosition(iworldreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    @Override
    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (!this.isValidPosition(p_196271_1_,p_196271_4_, p_196271_5_))
        {
            this.blockFall(p_196271_4_.getWorld(), p_196271_5_);
        }

        return super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{HORIZONTAL_FACING, OCCUPANCY});
    }

    static {
        HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
        OCCUPANCY = IntegerProperty.create("occupancy", 0, 8);
        SHAPE = Block.makeCuboidShape(3.0D, 6.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    }
}