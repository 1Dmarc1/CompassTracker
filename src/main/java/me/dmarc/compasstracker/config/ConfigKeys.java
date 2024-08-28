package me.dmarc.compasstracker.config;

public enum ConfigKeys {
    UPDATE_INTERVAL("updateInterval"),
    COMPASS_COOLDOWN("compassCooldown"),
    INCLUDE_CREATIVE("includeCreative"),
    INCLUDE_SPECTATOR("includeSpectator"),
    INCLUDE_ADVENTURE("includeAdventure"),
    INCLUDE_SURVIVAL("includeSurvival"),
    ;


    private final String key;

    ConfigKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
