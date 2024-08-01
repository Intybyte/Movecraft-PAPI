package me.vaan.movecraft.expansions

import net.countercraft.movecraft.craft.CraftManager
import org.bukkit.entity.Player

class MovecraftExpansion(private val craftManager: CraftManager) : AbstractMovecraftExpansion() {

    override fun getIdentifier(): String {
        return "movecraft"
    }

    override fun onPlaceholderRequest(player: Player?, params: String): String? {
        player ?: return null
        val craft = craftManager.getCraftByPlayer(player)
        craft ?: return "Not on craft"

        when(params) {
            "is_player_cruising" -> return if (craft.cruising) "True" else "False"
            "craft_name" -> return craft.name
            "craft_speed" -> {
                if (!craft.cruising) {
                    return "0"
                }

                return craft.speed.toString()
            }
            "craft_current_gear" -> return craft.currentGear.toString()
            "cruising_direction" -> return craft.cruiseDirection.toString()
            "burning_fuel" -> return craft.burningFuel.toString()
        }

        return null
    }
}
