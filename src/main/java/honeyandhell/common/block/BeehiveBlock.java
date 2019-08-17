package honeyandhell.common.block;

import honeyandhell.common.tileentity.BeehiveTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BeehiveBlock extends ContainerBlock
{
    protected static final VoxelShape SHAPE;
    protected static final VoxelShape SHAPE_BODY;
    protected static final VoxelShape SHAPE_TOP;

    public BeehiveBlock(Properties p_i48426_1_) {
        super(p_i48426_1_);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onBlockActivated(BlockState p_220051_1_, World p_220051_2_, BlockPos p_220051_3_, PlayerEntity p_220051_4_, Hand p_220051_5_, BlockRayTraceResult p_220051_6_) {
        if (p_220051_2_.isRemote) {
            return true;
        } else {
            TileEntity lvt_7_1_ = p_220051_2_.getTileEntity(p_220051_3_);
            if (lvt_7_1_ instanceof BeehiveTileEntity) {
                p_220051_4_.openContainer((BeehiveTileEntity)lvt_7_1_);
            }

            return true;
        }
    }

    @Override
    public void onReplaced(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
        if (p_196243_1_.getBlock() != p_196243_4_.getBlock()) {
            TileEntity lvt_6_1_ = p_196243_2_.getTileEntity(p_196243_3_);
            if (lvt_6_1_ instanceof IInventory) {
                InventoryHelper.dropInventoryItems(p_196243_2_, p_196243_3_, (IInventory)lvt_6_1_);
                p_196243_2_.updateComparatorOutputLevel(p_196243_3_, this);
            }

            super.onReplaced(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
        }
    }

    @Override
    public void tick(BlockState p_196267_1_, World p_196267_2_, BlockPos p_196267_3_, Random p_196267_4_) {
        TileEntity lvt_5_1_ = p_196267_2_.getTileEntity(p_196267_3_);
        if (lvt_5_1_ instanceof BeehiveTileEntity) {
            ((BeehiveTileEntity)lvt_5_1_).func_213962_h();
        }

    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader p_196283_1_) {
        return new BeehiveTileEntity();
    }

    @Override
    public void onBlockPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        if (p_180633_5_.hasDisplayName()) {
            TileEntity lvt_6_1_ = p_180633_1_.getTileEntity(p_180633_2_);
            if (lvt_6_1_ instanceof BeehiveTileEntity) {
                ((BeehiveTileEntity)lvt_6_1_).setCustomName(p_180633_5_.getDisplayName());
            }
        }
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState p_149740_1_) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
        return Container.calcRedstone(p_180641_2_.getTileEntity(p_180641_3_));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

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
    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
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
