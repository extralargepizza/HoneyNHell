package honeynhell.common.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class QueenBeeItem extends BeeItem
{
    public QueenBeeItem(Properties p_i48517_1_)
    {
        super(p_i48517_1_);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean hasEffect(ItemStack p_77636_1_) {
        return true;
    }
}
