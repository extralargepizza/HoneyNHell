package honeynhell.common.util.inventory;

import honeynhell.api.item.HNHItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupHNH extends ItemGroup
{
    public static final ItemGroupHNH instance = new ItemGroupHNH(ItemGroup.GROUPS.length, "honeynhell");

    private ItemGroupHNH(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(HNHItems.hnh_icon);
    }
}
