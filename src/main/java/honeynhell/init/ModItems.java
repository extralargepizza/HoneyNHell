package honeynhell.init;

import honeynhell.common.item.*;
import honeynhell.common.util.inventory.ItemGroupHNH;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static honeynhell.api.item.HNHItems.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
    public static final Food FILLED_HONEYCOMB = (new Food.Builder()).hunger(1).saturation(0.1F).fastToEat().build();
    public static final Food HONEY_ON_BREAD = (new Food.Builder()).hunger(3).saturation(0.4F).build();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        net = registerItem(new NetItem(new Item.Properties().group(ItemGroupHNH.instance).maxStackSize(1).maxDamage(64)), "net");

        honeycomb = registerItem(new Item(new Item.Properties().group(ItemGroupHNH.instance)), "honeycomb");
        filled_honeycomb = registerItem(new Item(new Item.Properties().group(ItemGroupHNH.instance).food(FILLED_HONEYCOMB)), "filled_honeycomb");

        raw_honey = registerItem(new Item(new Item.Properties().group(ItemGroupHNH.instance)), "raw_honey");
        honey_on_bread = registerItem(new Item(new Item.Properties().group(ItemGroupHNH.instance).food(HONEY_ON_BREAD)), "honey_on_bread");
        bottle_of_mead = registerItem(new BottleOfMeadItem(new Item.Properties().group(ItemGroupHNH.instance).maxStackSize(1)), "bottle_of_mead");

        beeswax = registerItem(new Item(new Item.Properties().group(ItemGroupHNH.instance)), "beeswax");
        royal_jelly = registerItem(new Item(new Item.Properties().group(ItemGroupHNH.instance)), "royal_jelly");

        bee_larva = registerItem(new BeeLarvaItem(new Item.Properties().group(ItemGroupHNH.instance).maxStackSize(1).rarity(Rarity.UNCOMMON)), "bee_larva");
        worker_bee = registerItem(new WorkerBeeItem(new Item.Properties().group(ItemGroupHNH.instance).maxStackSize(1).rarity(Rarity.UNCOMMON).maxDamage(16)), "worker_bee");
        drone_bee = registerItem(new DroneBeeItem(new Item.Properties().group(ItemGroupHNH.instance).maxStackSize(1).rarity(Rarity.UNCOMMON).maxDamage(1)), "drone_bee");
        queen_bee = registerItem(new QueenBeeItem(new Item.Properties().group(ItemGroupHNH.instance).maxStackSize(1).rarity(Rarity.EPIC).maxDamage(32)), "queen_bee");

        hnh_icon = registerItem(new Item(new Item.Properties()), "hnh_icon");
    }

    public static Item registerItem(Item item, String name)
    {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
