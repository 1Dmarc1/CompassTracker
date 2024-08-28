# CompassTracker

**CompassTracker** is a Minecraft Spigot plugin that transforms compasses into player-tracking devices. Instead of pointing to the world spawn, compasses will direct players to the nearest player, adding excitement to survival games, manhunts, and other multiplayer scenarios.

## Features

- **Track Nearest Player:** Compasses dynamically point to the closest player.
- **Customizable Update Interval:** Adjust how frequently the compass updates its direction.
- **Tracked Player's name:** Press right click with the compass in the main hand to display the tracked player.
- **Cooldown System:** Set a cooldown period to prevent compass spamming.
- **Game Mode Filters:** Exclude players in Creative, Spectator, or Adventure modes from being tracked.

## Installation

1. **Download** the latest release from the [Releases](https://github.com/YourUsername/CompassTracker/releases) page.
2. **Place** the `.jar` file into your server's `plugins` directory.
3. **Restart** your server to load the plugin.
4. **Configure** the plugin by editing the `config.yml` file in the `plugins/CompassTracker` directory.

## Configuration

You can customize CompassTracker through the `config.yml` file, which is generated upon first run. Below are the available configuration options:

### `updateInterval`
- **Description:** Determines how often the compass updates its target.
- **Type:** Integer (ticks)
- **Default:** `5`
- ```yaml
  updateInterval: 5
  ```

### `compassCooldown`
- **Description:** Sets the cooldown time between compass uses.
- **Type:** Integer (ticks)
- **Default:** `240`
- ```yaml
  compassCooldown: 240
  ```

### `includeCreative`
- **Description:** Whether players in Creative mode can be tracked.
- **Type:** Boolean
- **Default:** `false`
- ```yaml
  includeCreative: false
  ```

### `includeSpectator`
- **Description:** Whether players in Spectator mode can be tracked.
- **Type:** Boolean
- **Default:** `false`
- ```yaml
  includeSpectator: false
  ```

### `includeAdventure`
- **Description:** Whether players in Adventure mode can be tracked.
- **Type:** Boolean
- **Default:** `false`
- ```yaml
  includeAdventure: false
  ```

### `includeSurvival`
- **Description:** Whether players in Survival mode can be tracked.
- **Type:** Boolean
- **Default:** `true`
- ```yaml
  includeSurvival: true
  ```

## Compatibility
- **Minecraft Version:** Compatible with Minecraft 1.19 and above.
- **Dependencies:** None.

---

Feel free to contribute, suggest new features, or report bugs! Enjoy the hunt with **CompassTracker**! 🧭
