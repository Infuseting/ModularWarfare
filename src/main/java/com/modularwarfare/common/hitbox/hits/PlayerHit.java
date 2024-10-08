package com.modularwarfare.common.hitbox.hits;

import com.modularwarfare.common.hitbox.PlayerHitbox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;

public class PlayerHit extends BulletHit {

    public PlayerHitbox hitbox;

    public PlayerHit(PlayerHitbox box, RayTraceResult result, double distance, float remainingPenetrate, float remainingBlockPenetrate) {
        super(result, distance, remainingPenetrate, remainingBlockPenetrate);
        this.hitbox = box;
    }

    public EntityPlayer getEntity() {
        return hitbox.player;
    }
}
