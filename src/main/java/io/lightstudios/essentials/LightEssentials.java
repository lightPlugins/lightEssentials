package io.lightstudios.essentials;

import io.lightstudios.core.LightCore;
import io.lightstudios.core.commands.manager.CommandManager;
import io.lightstudios.core.util.files.FileManager;
import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.essentials.commands.GamemodeCommand;
import io.lightstudios.essentials.configs.MessageConfig;
import io.lightstudios.essentials.configs.SettingsConfig;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class LightEssentials extends JavaPlugin {

    public static LightEssentials instance;

    private final ArrayList<LightCommand> commands = new ArrayList<>();

    private MessageConfig messages;
    private SettingsConfig settings;

    public static String messagePrefix;

    private FileManager messageConfig;
    private FileManager settingsConfig;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        LightCore.instance.getConsolePrinter().printInfo("Enabling LightEssentials");
        generateConfigs();
        loadCommands();

    }

    @Override
    public void onDisable() {

    }

    private void loadCommands() {
        new CommandManager(new ArrayList<>(List.of(new GamemodeCommand())), "gmc");
    }

    private void generateConfigs() {
        this.settingsConfig = new FileManager(this, "settings.yml", true);
        this.settings = new SettingsConfig(this.settingsConfig);
        selectLanguage();
    }

    private void selectLanguage() {
        String language = settings.language();

        switch (language) {
            case "de":
                this.messageConfig = new FileManager(this, "language/" + "de" + ".yml", true);
                break;
            case "pl":
                this.messageConfig = new FileManager(this, "language/" + "pl" + ".yml", true);
                break;
            default:
                this.messageConfig = new FileManager(this, "language/" + "en" + ".yml", true);
                break;
        }

        this.messages = new MessageConfig(this.messageConfig);
        this.messagePrefix = messages.prefix();
    }
}