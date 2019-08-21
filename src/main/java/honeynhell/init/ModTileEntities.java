package honeynhell.init;

import honeynhell.api.block.HAHBlocks;
import honeynhell.common.tileentity.BeehiveTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities
{
    public static TileEntityType<BeehiveTileEntity> BEEHIVE;

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event)
    {
        BEEHIVE = registerTileEntityType(TileEntityType.Builder.<BeehiveTileEntity>create(BeehiveTileEntity::new, HAHBlocks.beehive).build(null), "beehive");
    }

    public static <T extends TileEntity> TileEntityType<T> registerTileEntityType(TileEntityType<T> tileEntityType, String name)
    {
        tileEntityType.setRegistryName(name);
        ForgeRegistries.TILE_ENTITIES.register(tileEntityType);
        return tileEntityType;
    }
}
