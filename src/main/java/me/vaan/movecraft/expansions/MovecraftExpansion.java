package me.vaan.movecraft.expansions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.countercraft.movecraft.craft.Craft;
import net.countercraft.movecraft.craft.CraftManager;
import net.countercraft.movecraft.craft.PlayerCraft;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MovecraftExpansion extends PlaceholderExpansion {
    @NotNull
    public String getAuthor() {
        return "Vaan1310";
    }

    @NotNull
    public String getVersion() {
        return "1.0.4";
    }

    @NotNull
    public String getRequiredPlugin() {
        return "Movecraft";
    }

    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin(this.getRequiredPlugin()) != null;
    }

    @NotNull
    public String getIdentifier() {
        return "movecraft";
    }

    @Nullable
    public String onPlaceholderRequest(@Nullable Player executor, @NotNull String params) {
        if (executor == null) {
            return null;
        }

        CraftManager manager = CraftManager.getInstance();
        if (manager == null) {
            return null;
        }

        PlayerCraft craft = Utils.craftOf(executor);
        if (craft == null) {
            return "Not on craft";
        }

        switch (params) {
            case "burning_fuel" -> {
                return String.valueOf(craft.getBurningFuel());
            }
            case "craft_speed" -> {
                if (!craft.getCruising()) {
                    return "0";
                }

                return String.valueOf(craft.getSpeed());
            }
            case "flyblocks" -> {
                var flyBlocks = craft.getDataTag(Craft.FLYBLOCKS);
                int sumFlyblocks = Utils.sumOf(flyBlocks.getKeySet(), flyBlocks::get);
                return String.valueOf(sumFlyblocks);
            }
            case "craft_name" -> {
                return craft.getName();
            }
            case "moveblocks" -> {
                var moveblocks = craft.getDataTag(Craft.MOVEBLOCKS);
                int sumMoveblocks = Utils.sumOf(moveblocks.getKeySet(), moveblocks::get);
                return String.valueOf(sumMoveblocks);
            }
            case "cruising_direction" -> {
                return craft.getCruiseDirection().toString();
            }
            case "is_player_pilot" -> {
                return craft.getPilot().equals(executor) ? "True" : "False";
            }
            case "is_player_cruising" -> {
                return craft.getCruising() ? "True" : "False";
            }
            case "craft_current_gear" -> {
                return String.valueOf(craft.getCurrentGear());
            }
            case "blocks_on_craft" -> {
                var materials = craft.getDataTag(Craft.MATERIALS);
                int sumMaterials = Utils.sumOf(materials.getKeySet(), materials::get);
                return String.valueOf(sumMaterials);
            }
        }

        return null;
    }
}
