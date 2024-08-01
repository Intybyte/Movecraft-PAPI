package me.vaan.movecraft.expansions

import me.clip.placeholderapi.expansion.PlaceholderExpansion

abstract class AbstractMovecraftExpansion : PlaceholderExpansion() {

    override fun getAuthor(): String {
        return "Vaan1310"
    }

    override fun getVersion(): String {
        return "1.0.0"
    }

    override fun persist(): Boolean {
        return true
    }
}