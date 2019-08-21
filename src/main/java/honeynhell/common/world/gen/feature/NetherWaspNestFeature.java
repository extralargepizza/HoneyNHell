package honeynhell.common.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import honeynhell.api.block.HAHBlocks;
import honeynhell.common.block.BeeNestBlock;
import honeynhell.common.util.block.IBlockPosQuery;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class NetherWaspNestFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos.up()).getBlock() == Blocks.NETHERRACK || world.getBlockState(pos.up()).getBlock() == Blocks.NETHER_QUARTZ_ORE;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).isAir(world, pos);
    protected int halfHeight = 7;
    protected int maxRadius = 9;
    protected int layerSize = 3;
    protected int bottomExtra = 4;
    protected float emptyChance = 0.25F;

    public NetherWaspNestFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random rand, BlockPos pos, NoFeatureConfig p_212245_5_)
    {
        int i = 0;

        for(int j = 0; j < 128; ++j)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(8) - rand.nextInt(8), rand.nextInt(8) - rand.nextInt(8));
            if (this.canPlaceHere(world, blockpos))
            {
                generate(world, rand, blockpos);
                ++i;
            }
        }

        return i > 0;
    }

    public void generate(IWorld world, Random rand, BlockPos pos)
    {
        boolean empty = rand.nextFloat() <= this.emptyChance;

        // create the top and bottom halves of the hive, and a little bit extra on the bottom for the sake of looking
        // slightly more like a hive
        for (int yOffset = this.halfHeight; yOffset >= -this.halfHeight - this.bottomExtra; yOffset--)
        {
            // y is already negative, so add it rather than subtract
            int radius = this.maxRadius + (yOffset / this.layerSize) * (yOffset >= 0 ? -1 : 1);

            for (int xOffset = -radius; xOffset <= radius; xOffset++)
            {
                for (int zOffset = -radius; zOffset <= radius; zOffset++)
                {
                    int x2_z2 = xOffset * xOffset + zOffset * zOffset;

                    // used to determine where to fill with honey
                    boolean bottomHalf = yOffset <= 0;

                    // check to fill the top and bottom of the two layers
                    boolean outerCap = yOffset == -this.halfHeight - this.bottomExtra || yOffset == this.halfHeight;
                    boolean innerCap = yOffset == -this.halfHeight - this.bottomExtra + 1 || yOffset == this.halfHeight - 1;
                    BlockPos realPos = pos.add(xOffset, yOffset - this.halfHeight, zOffset);

                    // create a circular outline for the hive. the bottom and top layers should be filled.
                    // add a bit extra (1) to make the circles nicer too

                    // inner layer. inset by one block
                    if (x2_z2 <= (radius - 1) * (radius - 1) + 1 && (x2_z2 >= (radius - 2) * (radius - 2) || innerCap))
                    {
                        BlockState honeycomb = HAHBlocks.empty_honeycomb_block.getDefaultState();
                        float f = rand.nextFloat();

                        if (!empty && f <= 0.95)
                        {
                            honeycomb = HAHBlocks.honeycomb_block.getDefaultState();

                            // if on the bottom half of the hive bias more towards filled honeycomb.
                            // the rest of the hive can still have filled blocks though
                            if (f <= 0.2 || (f <= 0.65 && bottomHalf))
                            {
                                honeycomb = HAHBlocks.filled_honeycomb_block.getDefaultState();
                            }
                        }
                        else if (empty && f <= 0.2)
                        {
                            honeycomb = Blocks.AIR.getDefaultState();
                        }

                        // offset so we're placing the hive underneath the initial y coordinate
                        world.setBlockState(realPos, honeycomb, 2);
                    }

                    // inner fill
                    if (!empty && bottomHalf && x2_z2 <= (radius - 2) * (radius - 2) + 1)
                    {
                        // fill the centre of the hive with honey blocks honey
                        //TODO: Replace water with liquid honey
                        BlockState fillBlock = yOffset == 0 ? HAHBlocks.crystallized_honey.getDefaultState() : Blocks.WATER.getDefaultState();

                        // only replace air blocks, not the hive layers
                        if (world.getBlockState(realPos).isAir(world, realPos))
                        {
                            world.setBlockState(realPos, fillBlock, 2);
                        }
                    }

                    // place a wasp spawner in the middle of the hive
                    //TODO: Add spawner
                    /*
                    if (!empty && yOffset == 0 && xOffset == 0 && zOffset == 0)
                    {
                        world.setBlockState(realPos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
                        TileEntity tileentity = world.getTileEntity(realPos);

                        if (tileentity instanceof TileEntityMobSpawner)
                        {
                            MobSpawnerBaseLogic spawnerLogic = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();

                            NBTTagCompound spawnerConfig = new NBTTagCompound();
                            spawnerConfig.setShort("MinSpawnDelay", (short)100);
                            spawnerConfig.setShort("MaxSpawnDelay", (short)250);
                            spawnerConfig.setShort("SpawnCount", (short)2);
                            spawnerConfig.setShort("MaxNearbyEntities", (short)10);
                            spawnerConfig.setShort("RequiredPlayerRange", (short)48);

                            spawnerLogic.readFromNBT(spawnerConfig);
                            spawnerLogic.setEntityId(new ResourceLocation(BiomesOPlenty.MOD_ID, "wasp"));
                        }
                        else
                        {
                            BiomesOPlenty.logger.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[] {Integer.valueOf(realPos.getX()), Integer.valueOf(realPos.getY()), Integer.valueOf(realPos.getZ())});
                        }
                    }
                    */

                    // outer layer
                    if (x2_z2 <= radius * radius + 1 && (x2_z2 >= (radius - 1) * (radius - 1) || outerCap))
                    {
                        // offset so we're placing the hive underneath the initial y coordinate
                        world.setBlockState(realPos, HAHBlocks.wasp_nest_block.getDefaultState(), 2);
                    }
                }
            }
        }
    }

    public boolean canPlaceHere(IWorld world, BlockPos pos)
    {
        if (pos.getY() <= 1 || pos.getY() > 255)
        {
            return false;
        }
        for (int y = this.halfHeight; y >= -this.halfHeight - this.bottomExtra; y--)
        {
            // y is already negative, so add it rather than subtract
            int radius = this.maxRadius + (y / this.layerSize) * (y >= 0 ? -1 : 1);

            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    if (x == 0 && z == 0 && y == this.halfHeight && !this.placeOn.matches(world,  pos.add(x, y - this.halfHeight, z)))
                    {
                        return false;
                    }

                    if (!this.replace.matches(world, pos.add(x, y - this.halfHeight, z)))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}