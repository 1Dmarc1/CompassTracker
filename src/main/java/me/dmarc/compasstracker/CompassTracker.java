package me.dmarc.compasstracker;

import me.dmarc.compasstracker.config.ConfigKeys;
import me.dmarc.compasstracker.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class CompassTracker extends JavaPlugin {
    private CompassUpdateTask compassUpdateTask;
    private CompassClickListener compassClickListener;

    @Override
    public void onEnable() {
        ConfigManager.init(this);

        this.compassUpdateTask = new CompassUpdateTask();
        this.compassClickListener = new CompassClickListener();

        int interval = (int) ConfigManager.get(ConfigKeys.UPDATE_INTERVAL);
        compassUpdateTask.start(this, interval);
        this.getServer().getPluginManager().registerEvents(compassClickListener, this);
    }

    @Override
    public void onDisable() {
        compassUpdateTask.stop();

        Bukkit.getScheduler().cancelTasks(this);
        HandlerList.unregisterAll(this);
    }
}
