package honeyandhell.init;

import honeyandhell.common.util.inventory.ItemGroupHAH;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static honeyandhell.api.item.HAHItems.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        bee_larvae = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "bee_larvae");
        worker_bee = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "worker_bee");
        drone_bee = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "drone_bee");
        queen_bee = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "queen_bee");

        honeycomb = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "honeycomb");
        filled_honeycomb = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "filled_honeycomb");
        raw_honey = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "raw_honey");
        beeswax = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "beeswax");
        royal_jelly = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "royal_jelly");

        hah_icon = registerItem(new Item(new Item.Properties()), "hah_icon");
    }

    public static Item registerItem(Item item, String name)
    {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
