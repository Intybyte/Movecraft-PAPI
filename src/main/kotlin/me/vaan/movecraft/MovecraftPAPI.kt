package me.vaan.movecraft

import me.vaan.movecraft.expansions.MovecraftExpansion
import net.countercraft.movecraft.craft.CraftManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class MovecraftPAPI : JavaPlugin() {

    override fun onEnable() {
        val craftManager: CraftManager
        if (Bukkit.getPluginManager().isPluginEnabled("Movecraft")) {
            craftManager = CraftManager.getInstance()
        } else {
            isEnabled = false
            return
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            MovecraftExpansion(craftManager).register()
            //SimpleTestPlaceholder().register()
        } else {
            isEnabled = false
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
