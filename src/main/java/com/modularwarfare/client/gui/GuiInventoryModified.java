package com.modularwarfare.client.gui;

import com.modularwarfare.ModularWarfare;
import com.modularwarfare.api.MWArmorType;
import com.modularwarfare.common.backpacks.ItemBackpack;
import com.modularwarfare.common.container.ContainerInventoryModified;
import com.modularwarfare.utility.ModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import scala.None;

import java.awt.*;
import java.lang.reflect.Field;


public class GuiInventoryModified extends InventoryEffectRenderer {
    public static final ResourceLocation ICONS;
    public static final ResourceLocation INVENTORY_BG;
    public static final ResourceLocation INVENTORY_ARMOR_TEXT_FIELD;
    public static final ResourceLocation INVENTORY_ARMOR_ARM_BAND;
    public static final ResourceLocation INVENTORY_ARMOR_BODY_ARMOR;
    public static final ResourceLocation INVENTORY_ARMOR_EAR_PIECE;
    public static final ResourceLocation INVENTORY_ARMOR_EYE_WEAR;
    public static final ResourceLocation INVENTORY_ARMOR_FACE_COVER;
    public static final ResourceLocation INVENTORY_ARMOR_HEAD_WEAR;
    public static final ResourceLocation INVENTORY_ARMOR_HOLSTER;
    public static final ResourceLocation INVENTORY_ARMOR_ON_SLING;
    public static final ResourceLocation INVENTORY_ARMOR_ON_BACK;
    public static final ResourceLocation INVENTORY_ARMOR_SCABBARD;
    public static final ResourceLocation INVENTORY_ARMOR_VEST;
    public static final ResourceLocation INVENTORY_ARMOR_BACKPACK;

    public static final ResourceLocation NONE;


    public static final ResourceLocation BCK_BLUE;
    public static final ResourceLocation BCK_BROWN;
    public static final ResourceLocation BCK_GREEN;
    public static final ResourceLocation BCK_GREY;
    public static final ResourceLocation BCK_ORANGE;
    public static final ResourceLocation BCK_PURPLE;
    public static final ResourceLocation BCK_RED;
    public static final ResourceLocation BCK_YELLOW;



    static {
        ICONS = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/icons.png");
        INVENTORY_BG = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/inventory.png");
        INVENTORY_ARMOR_ARM_BAND = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/arm_band.png");
        INVENTORY_ARMOR_TEXT_FIELD = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/text_field.png");
        INVENTORY_ARMOR_BODY_ARMOR = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/body_armor.png");
        INVENTORY_ARMOR_EAR_PIECE = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/ear_piece.png");
        INVENTORY_ARMOR_EYE_WEAR = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/eye_wear.png");
        INVENTORY_ARMOR_FACE_COVER = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/face_cover.png");
        INVENTORY_ARMOR_HEAD_WEAR = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/head_wear.png");
        INVENTORY_ARMOR_HOLSTER = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/holster.png");
        INVENTORY_ARMOR_ON_SLING = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/on_back.png");
        INVENTORY_ARMOR_ON_BACK = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/on_sling.png");
        INVENTORY_ARMOR_SCABBARD = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/scabbard.png");
        INVENTORY_ARMOR_VEST = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/vest.png");
        INVENTORY_ARMOR_BACKPACK = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/wear/backpack.png");
        NONE = new ResourceLocation(ModularWarfare.MOD_ID, "textures/none.png");

        //FOND ITEM
        BCK_BLUE = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/background/blue.png");
        BCK_BROWN = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/background/brown.png");
        BCK_GREEN = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/background/green.png");
        BCK_GREY = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/background/grey.png");
        BCK_ORANGE = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/background/orange.png");
        BCK_PURPLE = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/background/purple.png");
        BCK_RED = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/background/red.png");
        BCK_YELLOW = new ResourceLocation(ModularWarfare.MOD_ID, "textures/gui/background/yellow.png");
    }

    private float oldMouseX;
    private float oldMouseY;
    private int X_POS;
    private int Y_POS;

    public GuiInventoryModified(final EntityPlayer player) {
        super(new ContainerInventoryModified(player.inventory, !player.getEntityWorld().isRemote, player));
        this.allowUserInput = true;
        ScaledResolution scaledResolution  = new ScaledResolution(Minecraft.getMinecraft());
        this.xSize = scaledResolution.getScaledWidth();
        this.ySize = scaledResolution.getScaledHeight();

    }

    private void resetGuiLeft() {
        this.guiLeft = (this.width - this.xSize) / 2;
    }

    public void updateScreen() {
        this.updateActivePotionEffects();
        this.resetGuiLeft();
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        this.resetGuiLeft();
    }

    protected void drawGuiContainerForegroundLayer(final int mouseX, final int mouseY) {
        //this.fontRenderer.drawString(I18n.format("PlayerStats:", new Object[0]), 103, 10, 20210712);
        //this.fontRenderer.drawString(I18n.format("- Deaths ", new Object[0]), 103, 19, 20210712);
    }

    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        if (!(Minecraft.getMinecraft().player.openContainer instanceof ContainerInventoryModified)) {
            return;
        }
        this.drawDefaultBackground();
        this.oldMouseX = mouseX;
        this.oldMouseY = mouseY;
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        
    }

    protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 0.9f);
        ScaledResolution scaledResolution  = new ScaledResolution(Minecraft.getMinecraft());
        this.xSize = scaledResolution.getScaledWidth();
        this.ySize = scaledResolution.getScaledHeight();
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_BG);
        final int k = this.guiLeft;
        final int l = this.guiTop;
        //this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        final ContainerInventoryModified containter = (ContainerInventoryModified) Minecraft.getMinecraft().player.openContainer;
        final IItemHandler extra = (IItemHandler) containter.extra;
        X_POS = 476;
        Y_POS = 65;
        drawArmorSlot(extra);
        drawStorage(extra);
        drawContainer(extra);
        //if (extra.getStackInSlot(0).hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (EnumFacing) null)) {
        //    final ItemStack stack = extra.getStackInSlot(0);
        //    final IItemHandler backpackInv = (IItemHandler) stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (EnumFacing) null);
        //    int xP = 0;
        //    int yP = 0;
        //    final int x = k + 180;
        //    final int y = l + 18;
        //    this.mc.getTextureManager().bindTexture(GuiInventoryModified.ICONS);
        //    this.drawTexturedModalRect(x - 5, y - 18, 18, 0, 82, 18);
        //    this.drawTexturedModalRect(x - 5, y, 18, 5, 82, 18);
        //    for (int i = 0; i < backpackInv.getSlots(); ++i) {
        //        this.drawSlotBackground(x + xP * 18, -1 + y + yP * 18);
        //        if (++xP % 4 == 0) {
        //            xP = 0;
        //            ++yP;
        //            if (i + 1 < backpackInv.getSlots()) {
        //                this.drawTexturedModalRect(x - 5, y + yP * 18, 18, 5, 82, 18);
        //            }
        //        } else if (i + 1 >= backpackInv.getSlots()) {
        //            ++yP;
        //        }
        //    }
        //    this.drawTexturedModalRect(x - 5, -1 + y + yP * 18, 18, 33, 82, 5);
//
        //    if (stack != null) {
        //        ItemBackpack backpackItem = (ItemBackpack) stack.getItem();
        //        this.fontRenderer.drawString(backpackItem.type.displayName, x, y - 12, 16777215);
        //    }
        //    RenderHelper.disableStandardItemLighting();
        //    GlStateManager.color(1.0f, 1.0f, 1.0f);
        //}
        //GuiPlayerInventory.drawEntityOnScreen(k + 51, l + 75, 30, k + 51 - this.oldMouseX, l + 75 - 50 - this.oldMouseY, (EntityLivingBase) this.mc.player);
    }

    protected void actionPerformed(final GuiButton button) {
    }

    public void drawSlotBackground(final int x, final int y) {
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.ICONS);
        this.drawTexturedModalRect(x, y, 0, 0, 18, 18);
    }
    protected void drawArmorSlot(final IItemHandler extra) {
        drawTextField(
                I18n.format("mwf:gui.inventory.ear_piece"),
                ModUtil.EAR_PIECE_SLOT_X,
                ModUtil.EAR_PIECE_SLOT_Y,
                0,
                0
        );

        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_EAR_PIECE);
        drawSlot(
                ModUtil.EAR_PIECE_SLOT_X  ,
                ModUtil.EAR_PIECE_SLOT_Y  ,
                0,
                0,
                ModUtil.EAR_PIECE_SLOT_W,
                ModUtil.EAR_PIECE_SLOT_H
        );
        if (!extra.getStackInSlot(4).isEmpty()) {
            drawItem(
                    ModUtil.EAR_PIECE_SLOT_X,
                    ModUtil.EAR_PIECE_SLOT_Y,
                    ModUtil.EAR_PIECE_SLOT_W,
                    ModUtil.EAR_PIECE_SLOT_H,
                    extra.getStackInSlot(4)
            );
        }

        drawTextField(
                I18n.format("mwf:gui.inventory.head_wear"),
                ModUtil.HEAD_WEAR_SLOT_X,
                ModUtil.HEAD_WEAR_SLOT_Y,
                0,
                0
        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_HEAD_WEAR);
        drawSlot(
                ModUtil.HEAD_WEAR_SLOT_X  ,
                ModUtil.HEAD_WEAR_SLOT_Y  ,
                0,
                0,
                ModUtil.HEAD_WEAR_SLOT_W,
                ModUtil.HEAD_WEAR_SLOT_H
        );
        if (!extra.getStackInSlot(7).isEmpty()) {
            drawItem(
                    ModUtil.HEAD_WEAR_SLOT_X,
                    ModUtil.HEAD_WEAR_SLOT_Y,
                    ModUtil.HEAD_WEAR_SLOT_W,
                    ModUtil.HEAD_WEAR_SLOT_H,
                    extra.getStackInSlot(7)
            );
        }

        drawTextField(
                I18n.format("mwf:gui.inventory.face_cover"),
                ModUtil.FACE_COVER_SLOT_X,
                ModUtil.FACE_COVER_SLOT_Y,
                0,
                0
        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_FACE_COVER);
        drawSlot(
                ModUtil.FACE_COVER_SLOT_X ,
                ModUtil.FACE_COVER_SLOT_Y ,
                0,
                0,
                ModUtil.FACE_COVER_SLOT_W,
                ModUtil.FACE_COVER_SLOT_H
        );
        if (!extra.getStackInSlot(6).isEmpty()) {
            drawItem(
                    ModUtil.FACE_COVER_SLOT_X,
                    ModUtil.FACE_COVER_SLOT_Y,
                    ModUtil.FACE_COVER_SLOT_W,
                    ModUtil.FACE_COVER_SLOT_H,
                    extra.getStackInSlot(6)
            );
        }

        drawTextField(
                I18n.format("mwf:gui.inventory.arm_band"),
                ModUtil.ARM_BAND_SLOT_X,
                ModUtil.ARM_BAND_SLOT_Y,
                0,
                0
        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_ARM_BAND);
        drawSlot(
                ModUtil.ARM_BAND_SLOT_X   ,
                ModUtil.ARM_BAND_SLOT_Y   ,
                0,
                0,
                ModUtil.ARM_BAND_SLOT_W,
                ModUtil.ARM_BAND_SLOT_H
        );
        if (!extra.getStackInSlot(2).isEmpty()) {
            drawItem(
                    ModUtil.ARM_BAND_SLOT_X,
                    ModUtil.ARM_BAND_SLOT_Y,
                    ModUtil.ARM_BAND_SLOT_W,
                    ModUtil.ARM_BAND_SLOT_H,
                    extra.getStackInSlot(2)
            );
        }
        drawTextField(                I18n.format("mwf:gui.inventory.body_armor"),                ModUtil.BODY_ARMOR_SLOT_X,                ModUtil.BODY_ARMOR_SLOT_Y,                0,                0        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_BODY_ARMOR);
        drawSlot(
                ModUtil.BODY_ARMOR_SLOT_X ,
                ModUtil.BODY_ARMOR_SLOT_Y ,
                0,
                0,
                ModUtil.BODY_ARMOR_SLOT_W,
                ModUtil.BODY_ARMOR_SLOT_H
        );
        if (!extra.getStackInSlot(3).isEmpty()) {
            drawItem(
                    ModUtil.BODY_ARMOR_SLOT_X,
                    ModUtil.BODY_ARMOR_SLOT_Y,
                    ModUtil.BODY_ARMOR_SLOT_W,
                    ModUtil.BODY_ARMOR_SLOT_H,
                    extra.getStackInSlot(3)
            );
        }

        drawTextField(                I18n.format("mwf:gui.inventory.eye_wear"),                ModUtil.EYE_WEAR_SLOT_X,                ModUtil.EYE_WEAR_SLOT_Y,                0,                0        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_EYE_WEAR);
        drawSlot(                ModUtil.EYE_WEAR_SLOT_X   ,                ModUtil.EYE_WEAR_SLOT_Y   ,                0,                0,                ModUtil.EYE_WEAR_SLOT_W,                ModUtil.EYE_WEAR_SLOT_H        );
        if (!extra.getStackInSlot(5).isEmpty()) {
            drawItem(
                    ModUtil.EYE_WEAR_SLOT_X,
                    ModUtil.EYE_WEAR_SLOT_Y,
                    ModUtil.EYE_WEAR_SLOT_W,
                    ModUtil.EYE_WEAR_SLOT_H,
                    extra.getStackInSlot(5)
            );
        }
        drawTextField(
                I18n.format("mwf:gui.inventory.on_sling"),
                ModUtil.ON_SLING_SLOT_X,
                ModUtil.ON_SLING_SLOT_Y,
                0,
                0
        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_ON_SLING);
        drawSlot(                ModUtil.ON_SLING_SLOT_X   ,                ModUtil.ON_SLING_SLOT_Y   ,                0,                0,                ModUtil.ON_SLING_SLOT_W,                ModUtil.ON_SLING_SLOT_H        );
        if (!extra.getStackInSlot(10).isEmpty()) {
            drawItem(
                    ModUtil.ON_SLING_SLOT_X,
                    ModUtil.ON_SLING_SLOT_Y,
                    ModUtil.ON_SLING_SLOT_W,
                    ModUtil.ON_SLING_SLOT_H,
                    extra.getStackInSlot(10)
            );
        }

        drawTextField(
                I18n.format("mwf:gui.inventory.holster"),
                ModUtil.HOLSTER_SLOT_X,
                ModUtil.HOLSTER_SLOT_Y,
                0,
                0
        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_HOLSTER);
        drawSlot(                ModUtil.HOLSTER_SLOT_X    ,                ModUtil.HOLSTER_SLOT_Y    ,                0,                0,                ModUtil.HOLSTER_SLOT_W,                ModUtil.HOLSTER_SLOT_H        );
        if (!extra.getStackInSlot(8).isEmpty()) {
            drawItem(
                    ModUtil.HOLSTER_SLOT_X,
                    ModUtil.HOLSTER_SLOT_Y,
                    ModUtil.HOLSTER_SLOT_W,
                    ModUtil.HOLSTER_SLOT_H,
                    extra.getStackInSlot(8)
            );
        }
        drawTextField(
                I18n.format("mwf:gui.inventory.on_back"),
                ModUtil.ON_BACK_SLOT_X,
                ModUtil.ON_BACK_SLOT_Y,
                0,
                0
        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_ON_BACK);
        drawSlot(                ModUtil.ON_BACK_SLOT_X    ,                ModUtil.ON_BACK_SLOT_Y    ,                0,                0,                ModUtil.ON_BACK_SLOT_W,                ModUtil.ON_BACK_SLOT_H        );
        if (!extra.getStackInSlot(9).isEmpty()) {
            drawItem(
                    ModUtil.ON_BACK_SLOT_X,
                    ModUtil.ON_BACK_SLOT_Y,
                    ModUtil.ON_BACK_SLOT_W,
                    ModUtil.ON_BACK_SLOT_H,
                    extra.getStackInSlot(9)
            );
        }
        drawTextField(
                I18n.format("mwf:gui.inventory.scabbard"),
                ModUtil.SCABBARD_SLOT_X,
                ModUtil.SCABBARD_SLOT_Y,
                0,
                0
        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_SCABBARD);
        drawSlot(                ModUtil.SCABBARD_SLOT_X   ,                ModUtil.SCABBARD_SLOT_Y   ,                0,                0,                ModUtil.SCABBARD_SLOT_W,                ModUtil.SCABBARD_SLOT_H        );
        if (!extra.getStackInSlot(11).isEmpty()) {
            drawItem(
                    ModUtil.SCABBARD_SLOT_X,
                    ModUtil.SCABBARD_SLOT_Y,
                    ModUtil.SCABBARD_SLOT_W,
                    ModUtil.SCABBARD_SLOT_H,
                    extra.getStackInSlot(11)
            );
        }
    }

    protected void drawStorage(final IItemHandler extra) {

        drawTextField(
                I18n.format("mwf:gui.inventory.vest"),
                X_POS,
                Y_POS,
                0,
                0
        );

        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_VEST);
        drawSlot(
                X_POS  ,
                Y_POS  ,
                0,
                0,
                ModUtil.VEST_SLOT_W,
                ModUtil.VEST_SLOT_H
        );
        System.out.println(extra.getStackInSlot(1));
        if (!extra.getStackInSlot(1).isEmpty()) {
            System.out.println("Hello World !");
            drawItem(
                    X_POS,
                    Y_POS,
                    ModUtil.VEST_SLOT_W,
                    ModUtil.VEST_SLOT_H,
                    extra.getStackInSlot(1)
            );
        }
        Y_POS += ModUtil.VEST_SLOT_H;
        Y_POS += 23;

        drawTextField(
                I18n.format("mwf:gui.inventory.pouch"),
                X_POS,
                Y_POS,
                0,
                0
        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.ICONS);
        for (int i = 0 ;i < 4; i++) {
            drawSlot(X_POS + 40 *i, Y_POS, 0, 0, 60, 60);
        }

        Y_POS += 48;
        Y_POS += 23;

        drawTextField(
                I18n.format("mwf:gui.inventory.backpack"),
                X_POS,
                Y_POS,
                0,
                0
        );

        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_BACKPACK);
        drawSlot(
                X_POS  ,
                Y_POS  ,
                0,
                0,
                ModUtil.BACKPACK_SLOT_W,
                ModUtil.BACKPACK_SLOT_H
        );
        if (!extra.getStackInSlot(0).isEmpty()) {
            drawItem(
                    X_POS,
                    Y_POS,
                    ModUtil.BACKPACK_SLOT_W,
                    ModUtil.BACKPACK_SLOT_H,
                    extra.getStackInSlot(0)
            );
        }
    }
    protected void drawContainer(final IItemHandler extra) {
        int containerX = 843;
        int containerY = 52;
        drawTextField(
                I18n.format("mwf:gui.inventory.stash"),
                containerX,
                containerY,
                0,
                0
        );
        this.mc.getTextureManager().bindTexture(GuiInventoryModified.ICONS);
        for (int i = 0 ;i < 10; i++) {
            for (int k = 0; k < 15; k++) {
                drawSlot(containerX + 40 *i, containerY + 44 * k, 0, 0, 60, 60);
            }
        }
    }
    protected void drawSlot(int x, int y, int textureX, int textureY, int width, int height) {
        // Calcul des positions et dimensions redimensionnées
        int newX = (int) calcPositionWidth(x);
        int newY = (int) calcPositionHeight(y);
        int newWidth = (int) calcPositionWidth(width);
        int newHeight = (int) calcPositionHeight(height);

        // Affichage du slot avec les nouvelles valeurs
        this.drawTexturedModalRect(newX, newY, textureX, textureY, newWidth, newHeight);
    }
    protected void drawItem(int x, int y, int width, int height, ItemStack itemStack) {
        int newX = (int) calcPositionWidth(x);
        int newY = (int) calcPositionHeight(y);
        int newWidth = (int) calcPositionWidth(width);
        int newHeight = (int) calcPositionHeight(height);
        ResourceLocation resourceLocation = itemStack.getItem().getRegistryName();
        TextureMap textureMap = Minecraft.getMinecraft().getTextureMapBlocks();
        String ressourceName = resourceLocation.toString();
        System.out.println(ressourceName);
        try {
            Class<?> clazz = textureMap.getClass();
            Field field = clazz.getDeclaredField("mapRegisteredSprites");
            field.setAccessible(true);
            Object value = field.get(textureMap);
            String[] parts = value.toString().split("\\{name='");

            for (String part : parts) {

                String[] result = part.split("', frameCount=");
                String[] items = ressourceName.split(":");

                if (result[0].contains(items[0]) && result[0].contains(items[1])) {
                    ressourceName = result[0];
                }
            }

        } catch (NoSuchFieldException e) {
            System.err.println("Field not found: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Illegal access to field: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextureAtlasSprite sprite = textureMap.getAtlasSprite(ressourceName);
        if (sprite != null) {

            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            this.drawTexturedModalRect(newX, newY, sprite, newWidth, newHeight);

        }
        else {
            Minecraft.getMinecraft().getTextureManager().bindTexture(NONE);
            this.drawTexturedModalRect(newX, newY, 0,0, newWidth, newHeight);
        }
    }

    protected void drawTextField(String text_field, int x, int y, int textureX, int textureY) {

        int newX = (int) calcPositionWidth(x);
        int newY = (int) calcPositionHeight(y-12);
        int newWidth = (int) calcPositionWidth(86);
        int newHeight = (int) calcPositionHeight(13);

        this.mc.getTextureManager().bindTexture(GuiInventoryModified.INVENTORY_ARMOR_TEXT_FIELD);
        this.drawTexturedModalRect(newX, newY, textureX, textureY, newWidth, newHeight);
        GlStateManager.pushMatrix();
        float scale = 0.75F;
        GlStateManager.scale(scale, scale, scale);
        String text = textFormation(text_field, newWidth);
        this.fontRenderer.drawString(text, (int)((newX + 5) / scale),  (int)((newY + 2) / scale), 0xFFFFFF);
        GlStateManager.popMatrix();
    }
    protected String textFormation(String text_field, final int width) {
        int charWidth = (4*2);
        if (Minecraft.getMinecraft().gameSettings.guiScale != 0) {
            charWidth = (Minecraft.getMinecraft().gameSettings.guiScale*2);
        }
         // Approximation de la largeur d'un caractère en pixels
        int maxCharsPerLine = width / charWidth;
        if (text_field.length() <= maxCharsPerLine) {
            return text_field;
        }
        String truncatedText = "...";
        if (maxCharsPerLine - 3 > 0) {
            truncatedText = text_field.substring(0, maxCharsPerLine - 3) + "...";
        }

        return truncatedText;
    }

    protected double calcPositionWidth(final int widthV) {
        double widthRatio = (double) this.width / 1280;
        return widthRatio*widthV;
    }
    protected double calcPositionHeight(final int heightV) {
        double heightRatio = (double) this.height / 800;
        return heightRatio*heightV;
    }

}
