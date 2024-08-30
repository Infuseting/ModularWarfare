package com.modularwarfare.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class GuiInGame extends Gui {


    public GuiInGame() {
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH || event.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO ||event.getType() == RenderGameOverlayEvent.ElementType.BOSSHEALTH || event.getType() == RenderGameOverlayEvent.ElementType.ARMOR || event.getType() == RenderGameOverlayEvent.ElementType.HEALTH || event.getType() == RenderGameOverlayEvent.ElementType.FOOD || event.getType() == RenderGameOverlayEvent.ElementType.AIR || event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR || event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE || event.getType() == RenderGameOverlayEvent.ElementType.HEALTHMOUNT || event.getType() == RenderGameOverlayEvent.ElementType.JUMPBAR || event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS) {
            event.setCanceled(true);
        }



    }
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void HitboxDisable(InputEvent.KeyInputEvent event) {

        //if (Keyboard.isKeyDown(Keyboard.KEY_F3) && Keyboard.isKeyDown(Keyboard.KEY_B)) {
        //    event.setCanceled(true);
        //}
        if (Minecraft.getMinecraft().gameSettings.keyBindTogglePerspective.isPressed()) {

            Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
        }
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int x = sr.getScaledWidth();
        int y = sr.getScaledHeight();


        if (Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
            String text = "Crie: \"!\" || Action: \"*\" || Chuchotter: \"$\" || HRP: \"(\" || De: \"/roll\"";
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            Gui.drawRect(2, y - 28, fontRenderer.getStringWidth(text) + 4 + 3, y + fontRenderer.FONT_HEIGHT + 6 + 2 - 33, 0x80000000);
            fontRenderer.drawStringWithShadow(text, 5, y - 25, Color.WHITE.getRGB());

        }

    }

}

