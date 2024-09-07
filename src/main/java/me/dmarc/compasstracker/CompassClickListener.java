package me.dmarc.compasstracker;

import me.dmarc.compasstracker.config.ConfigKeys;
import me.dmarc.compasstracker.config.ConfigManager;
import me.dmarc.compasstracker.util.Tuple;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CompassClickListener implements Listener {
    private final int cooldownTicks;

    public CompassClickListener() {
        this.cooldownTicks = (int) ConfigManager.get(ConfigKeys.COMPASS_COOLDOWN);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            Material mainHandItem = player.getInventory().getItemInMainHand().getType();

            if (player.getCooldown(mainHandItem) > 0) {
                return;
            }

            if (mainHandItem == Material.COMPASS) {
                Tuple<Player, Double> trackedPlayer = CompassUpdateTask.getTrackedPlayer(player);
                if (trackedPlayer == null) {
                    trackedPlayer = new Tuple<>(event.getPlayer(), 0.0);
                }

                long distance = Math.round(trackedPlayer.second());
                player.sendMessage("Tracking: " + trackedPlayer.first().getName() + " (" + distance + " blocks)");
                player.setCooldown(mainHandItem, cooldownTicks); //Set cooldown
            }
        }
    }
}
