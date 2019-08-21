package honeynhell.common.util.inventory;

import honeynhell.api.item.HAHItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupHAH extends ItemGroup
{
    public static final ItemGroupHAH instance = new ItemGroupHAH(ItemGroup.GROUPS.length, "honeynhell");

    private ItemGroupHAH(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(HAHItems.hah_icon);
    }
}
