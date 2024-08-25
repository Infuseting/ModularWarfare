package com.modularwarfare.common.container;

import com.modularwarfare.api.MWArmorType;
import com.modularwarfare.common.armor.ItemSpecialArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotEyeWear extends SlotItemHandler {
    private int width;
    private int height;
    public SlotEyeWear(final IItemHandler inv, final int index, final int xPosition, final int yPosition, final int width, final int height) {
        super(inv, index, xPosition, yPosition);
        this.height = height;
        this.width = width;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(@Nonnull final ItemStack stack) {
        if (stack.getItem() instanceof ItemSpecialArmor) {
            ItemSpecialArmor armor = (ItemSpecialArmor) stack.getItem();
            return (armor.armorType == MWArmorType.EyeWear);
        }
        return false;
    }
}
