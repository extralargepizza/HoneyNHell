package honeyandhell.init;

import honeyandhell.common.item.BeeItem;
import honeyandhell.common.item.BeeLarvaeItem;
import honeyandhell.common.item.NetItem;
import honeyandhell.common.util.inventory.ItemGroupHAH;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static honeyandhell.api.item.HAHItems.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
    public static final Food FILLED_HONEYCOMB = (new Food.Builder()).hunger(2).saturation(0.4F).fastToEat().build();
    public static final Food RAW_HONEY = (new Food.Builder()).hunger(1).saturation(0.2F).fastToEat().build();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        bee_larvae = registerItem(new BeeLarvaeItem(new Item.Properties().group(ItemGroupHAH.instance).maxStackSize(1).rarity(Rarity.UNCOMMON)), "bee_larvae");
        worker_bee = registerItem(new BeeItem(new Item.Properties().group(ItemGroupHAH.instance).maxStackSize(1).rarity(Rarity.UNCOMMON).maxDamage(8)), "worker_bee");
        drone_bee = registerItem(new BeeItem(new Item.Properties().group(ItemGroupHAH.instance).maxStackSize(1).rarity(Rarity.UNCOMMON).maxDamage(1)), "drone_bee");
        queen_bee = registerItem(new BeeItem(new Item.Properties().group(ItemGroupHAH.instance).maxStackSize(1).rarity(Rarity.EPIC).maxDamage(16)), "queen_bee");

        honeycomb = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "honeycomb");
        filled_honeycomb = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance).food(FILLED_HONEYCOMB)), "filled_honeycomb");
        raw_honey = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance).food(RAW_HONEY)), "raw_honey");
        royal_jelly = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "royal_jelly");
        beeswax = registerItem(new Item(new Item.Properties().group(ItemGroupHAH.instance)), "beeswax");

        net = registerItem(new NetItem(new Item.Properties().group(ItemGroupHAH.instance).maxStackSize(1).maxDamage(32)), "net");

        hah_icon = registerItem(new Item(new Item.Properties()), "hah_icon");
    }

    public static Item registerItem(Item item, String name)
    {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
