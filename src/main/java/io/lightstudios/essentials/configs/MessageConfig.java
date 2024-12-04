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
    public String gameModeChangeOther() { return config.getString("gameModeChangeOther"); }
    public String gameModeChangeTarget() { return config.getString("gameModeChangeTarget"); }

}
