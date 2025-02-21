package me.vaan.movecraft.expansions;

import net.countercraft.movecraft.craft.CraftManager;
import net.countercraft.movecraft.craft.PlayerCraft;
import net.countercraft.movecraft.util.hitboxes.HitBox;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class Utils {
    public static <T> int sumOf(Iterable<T> iterable, Function<T, Integer> function) {
        int sum = 0;
        for (T next : iterable) {
            sum += function.apply(next);
        }

        return sum;
    }

    public static PlayerCraft craftOf(Player player) {
        Location pos = player.getLocation();
        for (PlayerCraft craft : CraftManager.getInstance().getPlayerCraftsInWorld(pos.getWorld())) {
            if (craft.getPilot().equals(player)) {
                return craft;
            }

            HitBox hb = craft.getHitBox();
            for (Player entity : hb.getMidPoint().toBukkit(craft.getWorld()).getNearbyPlayers(
                    hb.getXLength() / 2.0 + 1,
                    hb.getYLength() / 2.0 + 2,
                    hb.getZLength() / 2.0 + 1
            )) {
                if (entity.equals(player)) {
                    return craft;
                }
            }
        }

        return null;
    }
}
