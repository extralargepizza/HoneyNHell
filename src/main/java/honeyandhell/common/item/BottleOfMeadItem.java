package honeyandhell.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BottleOfMeadItem extends Item
{
    public BottleOfMeadItem(Item.Properties p_i48476_1_)
    {
        super(p_i48476_1_);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack p_77654_1_, World p_77654_2_, LivingEntity p_77654_3_) {
        PlayerEntity lvt_4_1_ = p_77654_3_ instanceof PlayerEntity ? (PlayerEntity)p_77654_3_ : null;
        if (lvt_4_1_ == null || !lvt_4_1_.abilities.isCreativeMode) {
            p_77654_1_.shrink(1);
        }

        if (lvt_4_1_ instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)lvt_4_1_, p_77654_1_);
        }

        if (!p_77654_2_.isRemote) {
            EffectInstance effect = new EffectInstance(Effects.NAUSEA, 600);

            if (effect.getPotion().isInstant())
            {
                effect.getPotion().affectEntity(lvt_4_1_, lvt_4_1_, p_77654_3_, effect.getAmplifier(), 1.0D);
            }
            else
            {
                p_77654_3_.addPotionEffect(new EffectInstance(effect));
            }
        }

        if (lvt_4_1_ != null) {
            lvt_4_1_.addStat(Stats.ITEM_USED.get(this));
        }

        if (lvt_4_1_ == null || !lvt_4_1_.abilities.isCreativeMode) {
            if (p_77654_1_.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (lvt_4_1_ != null) {
                lvt_4_1_.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return p_77654_1_;
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack p_77661_1_) {
        return UseAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        p_77659_2_.setActiveHand(p_77659_3_);
        return new ActionResult(ActionResultType.SUCCESS, p_77659_2_.getHeldItem(p_77659_3_));
    }
}
