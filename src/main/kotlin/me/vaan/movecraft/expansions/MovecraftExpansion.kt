package me.vaan.movecraft.expansions

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import net.countercraft.movecraft.craft.Craft
import net.countercraft.movecraft.craft.CraftManager
import net.countercraft.movecraft.craft.type.RequiredBlockEntry
import net.countercraft.movecraft.util.Counter
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class MovecraftExpansion : PlaceholderExpansion() {

    override fun getAuthor(): String {
        return "Vaan1310"
    }

    override fun getVersion(): String {
        return "1.0.3"
    }

    override fun getRequiredPlugin(): String {
        return "Movecraft"
    }

    override fun canRegister(): Boolean {
        return Bukkit.getPluginManager().getPlugin(requiredPlugin) != null
    }

    override fun getIdentifier(): String {
        return "movecraft"
    }

    override fun onPlaceholderRequest(executor: Player?, params: String): String? {
        executor ?: return null

        val manager = CraftManager.getInstance()
        manager ?: return null

        val craft = executor.craft
        craft ?: return "Not on craft"

        when(params) {
            "is_player_pilot" -> {
                return if (craft.pilot == executor) "True" else "False"
            }

            "moveblocks" -> {
                val counter: Counter<RequiredBlockEntry> = craft.getDataTag(Craft.MOVEBLOCKS)
                val sum = counter.keySet.sumOf(counter::get)
                return sum.toString()
            }

            "flyblocks" -> {
                val counter: Counter<RequiredBlockEntry> = craft.getDataTag(Craft.FLYBLOCKS)
                val sum = counter.keySet.sumOf(counter::get)
                return sum.toString()
            }

            "blocks_on_craft" ->  {
                val counter = craft.getDataTag(Craft.MATERIALS)
                var total = 0

                for (material in counter.keySet) {
                    total += counter.get(material)
                }

                return "$total"
            }

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
