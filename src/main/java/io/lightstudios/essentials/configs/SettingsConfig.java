package io.lightstudios.essentials.configs;

import io.lightstudios.core.util.files.FileManager;
import org.bukkit.configuration.file.FileConfiguration;

public class SettingsConfig {

    private final FileConfiguration config;

    public SettingsConfig(FileManager selectedLanguage) {
        this.config = selectedLanguage.getConfig();
    }

    public String language() { return config.getString("language"); }

}
