package me.dmarc.compasstracker;

import me.dmarc.compasstracker.config.ConfigKeys;
import me.dmarc.compasstracker.config.ConfigManager;
import me.dmarc.compasstracker.util.Tuple;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.HashMap;

public class CompassUpdateTask implements Runnable {
    private static final HashMap<Player, Tuple<Player, Double>> tracking = new HashMap<>();
    private int taskID = -1;
    private boolean includeCreative;
    private boolean includeSpectator;
    private boolean includeAdventure;
    private boolean includeSurvival;

    public static Tuple<Player, Double> getTrackedPlayer(Player player) {
        return tracking.get(player);
    }

    @Override
    public void run() {
        Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

        for (Player self : onlinePlayers) {
            double closestPlayerdistance = Double.MAX_VALUE;
            Player closestPlayer = null;

            for (Player other : onlinePlayers) {

                if (shouldSkip(self, other)) {
                    continue;
                }

                double distance = getDistance(self.getLocation(), other.getLocation());
                if (distance < closestPlayerdistance) {
                    closestPlayerdistance = distance;
                    closestPlayer = other;
                }
            }

            if (closestPlayer == null) {
                closestPlayer = self;
                closestPlayerdistance = 0;
            }
            setCompassDirection(self, closestPlayer.getLocation());
            tracking.put(self, new Tuple<>(closestPlayer, closestPlayerdistance));
        }
    }

    public void start(Plugin plugin, int intervalInTicks) {
        if (taskID == -1) {
            this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, intervalInTicks);

            includeCreative = (boolean) ConfigManager.get(ConfigKeys.INCLUDE_CREATIVE);
            includeSpectator = (boolean) ConfigManager.get(ConfigKeys.INCLUDE_SPECTATOR);
            includeAdventure = (boolean) ConfigManager.get(ConfigKeys.INCLUDE_ADVENTURE);
            includeSurvival = (boolean) ConfigManager.get(ConfigKeys.INCLUDE_SURVIVAL);
        }
    }

    public void stop() {
        if (taskID != -1) {
            Bukkit.getScheduler().cancelTask(taskID);
            taskID = -1;
        }
    }

    public static double getDistance(Location loc1, Location loc2) {
        return Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) + Math.pow(loc1.getZ() - loc2.getZ(), 2));
    }

    private void setCompassDirection(Player player, Location location) {
        if (player != null && location != null) {
            player.setCompassTarget(location);
        }
    }

    private boolean shouldSkip(Player self, Player other) {
        //Check if self and other are the same player
        if (self.equals(other)) {
            return true;
        }

        //Check if both players are in the same world
        if (!self.getWorld().equals(other.getWorld())) {
            return true;
        }

        if (other.getGameMode().equals(GameMode.CREATIVE) && !includeCreative) {
            return true;
        }

        if (other.getGameMode().equals(GameMode.SPECTATOR) && !includeSpectator) {
            return true;
        }

        if (other.getGameMode().equals(GameMode.ADVENTURE) && !includeAdventure) {
            return true;
        }

        return other.getGameMode().equals(GameMode.SURVIVAL) && !includeSurvival;
    }
}
