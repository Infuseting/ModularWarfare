package com.modularwarfare.common.container;

import com.modularwarfare.common.backpacks.ItemBackpack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * Backpack slot in the players inventory
 */
public class SlotBackpack extends SlotItemHandler {
    private int width;
    private int height;
    public SlotBackpack(final IItemHandler inv, final int index, final int xPosition, final int yPosition, final int width, final int height) {
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
        return stack.getItem() instanceof ItemBackpack;
    }
}
