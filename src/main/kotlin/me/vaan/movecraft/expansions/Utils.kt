package me.vaan.movecraft.expansions

import net.countercraft.movecraft.craft.CraftManager
import net.countercraft.movecraft.craft.PlayerCraft
import org.bukkit.entity.Player

val Player.craft get() : PlayerCraft? {
    val pos = this.location
    for (craft in CraftManager.getInstance().getPlayerCraftsInWorld(pos.world)) {
        if (craft.pilot == this) {
            return craft
        }

        val hb = craft.hitBox
        for (entity in hb.midPoint.toBukkit(craft.world).getNearbyPlayers(
            hb.xLength / 2.0 + 1,
            hb.yLength / 2.0 + 2,
            hb.zLength / 2.0 + 1
        )) {
            if (entity == this) {
                return craft
            }
        }
    }

    return null
}