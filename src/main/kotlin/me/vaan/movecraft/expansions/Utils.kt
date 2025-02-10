package me.vaan.movecraft.expansions

import net.countercraft.movecraft.craft.CraftManager
import net.countercraft.movecraft.craft.PlayerCraft
import org.bukkit.entity.Player

val Player.craft get() : PlayerCraft? {
    val pos = this.location
    for (craft in CraftManager.getInstance().getPlayerCraftsInWorld(pos.world)) {
        if (craft.hitBox.contains(pos.blockX, pos.blockY, pos.blockZ)) {
            return craft
        }
    }

    return null
}