package io.lightstudios.essentials;

import io.lightstudios.core.LightCore;
import io.lightstudios.core.commands.manager.CommandManager;
import io.lightstudios.core.util.files.FileManager;
import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.essentials.commands.gamemode.AdventureCommand;
import io.lightstudios.essentials.commands.gamemode.CreativeCommand;
import io.lightstudios.essentials.commands.gamemode.SpectatorCommand;
import io.lightstudios.essentials.commands.gamemode.SurvivalCommand;
import io.lightstudios.essentials.commands.teleport.TpHereCommand;
import io.lightstudios.essentials.commands.util.FlyCommand;
import io.lightstudios.essentials.commands.util.HealCommand;
import io.lightstudios.essentials.commands.util.SpeedCommand;
import io.lightstudios.essentials.commands.world.DayCommand;
import io.lightstudios.essentials.commands.world.MorningCommand;
import io.lightstudios.essentials.commands.world.NightCommand;
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
        new CommandManager(new ArrayList<>(List.of(new CreativeCommand())), "gmc");
        new CommandManager(new ArrayList<>(List.of(new SurvivalCommand())), "gms");
        new CommandManager(new ArrayList<>(List.of(new AdventureCommand())), "gma");
        new CommandManager(new ArrayList<>(List.of(new SpectatorCommand())), "gmsp");

        new CommandManager(new ArrayList<>(List.of(new SpeedCommand())), "speed");
        new CommandManager(new ArrayList<>(List.of(new FlyCommand())), "fly");
        new CommandManager(new ArrayList<>(List.of(new HealCommand())), "heal");

        new CommandManager(new ArrayList<>(List.of(new DayCommand())), "day");
        new CommandManager(new ArrayList<>(List.of(new NightCommand())), "night");
        new CommandManager(new ArrayList<>(List.of(new MorningCommand())), "morning");

        new CommandManager(new ArrayList<>(List.of(new TpHereCommand())), "tphere");
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