package me.vaan.movecraft.expansions

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.Bukkit



abstract class AbstractMovecraftExpansion : PlaceholderExpansion() {

    override fun getAuthor(): String {
        return "Vaan1310"
    }

    override fun getVersion(): String {
        return "1.0.0"
    }

    override fun getRequiredPlugin(): String {
        return "Movecraft"
    }

    override fun canRegister(): Boolean {
        return Bukkit.getPluginManager().getPlugin(requiredPlugin) != null
    }

    override fun persist(): Boolean {
        return true
    }
}