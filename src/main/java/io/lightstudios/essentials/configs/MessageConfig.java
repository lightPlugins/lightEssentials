package io.lightstudios.essentials.configs;

import io.lightstudios.core.util.files.FileManager;
import org.bukkit.configuration.file.FileConfiguration;

public class MessageConfig {

    private final FileConfiguration config;

    public MessageConfig(FileManager selectedLanguage) {
        this.config = selectedLanguage.getConfig();
    }

    public int version() { return config.getInt("version"); }

    public String prefix() { return config.getString("prefix"); }
    public String noPermission() { return config.getString("noPermission"); }
    public String wrongSyntax() { return config.getString("wrongSyntax"); }
    public String gameModeChange() { return config.getString("gameModeChange"); }
    public String playerNotFound() { return config.getString("playerNotFound"); }
    public String worldNotFound() { return config.getString("worldNotFound"); }
    public String gameModeChangeOther() { return config.getString("gameModeChangeOther"); }
    public String gameModeChangeTarget() { return config.getString("gameModeChangeTarget"); }
    public String changeSpeed() { return config.getString("changeSpeed"); }
    public String maxOrMinSpeed() { return config.getString("maxOrMinSpeed"); }
    public String changeSpeedOther() { return config.getString("changeSpeedOther"); }
    public String changeSpeedTarget() { return config.getString("changeSpeedTarget"); }
    public String toggleFly() { return config.getString("toggleFly"); }
    public String toggleFlyOther() { return config.getString("toggleFlyOther"); }
    public String toggleFlyTarget() { return config.getString("toggleFlyTarget"); }
    public String heal() { return config.getString("heal"); }
    public String healOther() { return config.getString("healOther"); }
    public String feed() { return config.getString("feed"); }
    public String feedOther() { return config.getString("feedOther"); }
    public String healTarget() { return config.getString("healTarget"); }
    public String day() { return config.getString("day"); }
    public String dayWorld() { return config.getString("dayWorld"); }
    public String night() { return config.getString("night"); }
    public String nightWorld() { return config.getString("nightWorld"); }
    public String morning() { return config.getString("morning"); }
    public String morningWorld() { return config.getString("morningWorld"); }
    public String tpHere() { return config.getString("tpHere"); }
    public String tpHereTarget() { return config.getString("tpHereTarget"); }
    public String tp() { return config.getString("tp"); }
    public String setSpawn() { return config.getString("setSpawn"); }

}
