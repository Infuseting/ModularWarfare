package com.modularwarfare.api;

import com.google.gson.annotations.SerializedName;

import net.minecraft.inventory.EntityEquipmentSlot;

public enum MWArmorType {

    @SerializedName("head") Head,
    @SerializedName("chest") Chest,
    @SerializedName("legs") Legs,
    @SerializedName("feet") Feet,
    @SerializedName("vest") Vest(1),
    @SerializedName("armband") ArmBand(2),
    @SerializedName("bodyarmor") BodyArmor(3),
    @SerializedName("earpiece") EarPiece(4),
    @SerializedName("eyewear") EyeWear(5),
    @SerializedName("facecover") FaceCover(6),
    @SerializedName("headwear") HeadWear(7),
    @SerializedName("holster") Holster(8),
    @SerializedName("onback") OnBack(9),
    @SerializedName("onsling") OnSling(10),
    @SerializedName("scabbard") Scabbard(11);

    int[] validSlots ;

    public static MWArmorType fromVanillaSlot(EntityEquipmentSlot entityEquipmentSlot) {
        if(entityEquipmentSlot==EntityEquipmentSlot.HEAD) {
            return Head;
        }
        if(entityEquipmentSlot==EntityEquipmentSlot.CHEST) {
            return Chest;
        }
        if(entityEquipmentSlot==EntityEquipmentSlot.LEGS) {
            return Legs;
        }
        if(entityEquipmentSlot==EntityEquipmentSlot.FEET) {
            return Feet;
        }
        return null;
    }

    private MWArmorType(int... validSlots) {
        this.validSlots = validSlots;
    }

    public static boolean isVanilla(MWArmorType type) {
        return type == Head || type == Chest || type == Legs || type == Feet;
    }

    public boolean hasSlot(int slot) {
        for (int s : validSlots) {
            if (s == slot) return true;
        }
        return false;
    }

    public int[] getValidSlots() {
        return validSlots;
    }

}