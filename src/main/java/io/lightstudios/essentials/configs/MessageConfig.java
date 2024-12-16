package io.lightstudios.essentials.configs;

import io.lightstudios.core.LightCore;
import io.lightstudios.core.util.files.FileManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MessageConfig {

    private final FileConfiguration config;

    public MessageConfig(FileManager selectedLanguage) {
        this.config = selectedLanguage.getConfig();
    }

    public int version() { return config.getInt("version"); }

    public String prefix() { return config.getString("prefix"); }
    public List<String> noPermission() { return toStringList(config.get("noPermission")); }
    public List<String> wrongSyntax() { return toStringList(config.get("wrongSyntax")); }
    public List<String> gameModeChange() { return toStringList(config.get("gameModeChange")); }
    public List<String> playerNotFound() { return toStringList(config.get("playerNotFound")); }
    public List<String> worldNotFound() { return toStringList(config.get("worldNotFound")); }
    public List<String> gameModeChangeOther() { return toStringList(config.get("gameModeChangeOther")); }
    public List<String> gameModeChangeTarget() { return toStringList(config.get("gameModeChangeTarget")); }
    public List<String> changeSpeed() { return toStringList(config.get("changeSpeed")); }
    public List<String> maxOrMinSpeed() { return toStringList(config.get("maxOrMinSpeed")); }
    public List<String> changeSpeedOther() { return toStringList(config.get("changeSpeedOther")); }
    public List<String> changeSpeedTarget() { return toStringList(config.get("changeSpeedTarget")); }
    public List<String> toggleFly() { return toStringList(config.get("toggleFly")); }
    public List<String> toggleFlyOther() { return toStringList(config.get("toggleFlyOther")); }
    public List<String> toggleFlyTarget() { return toStringList(config.get("toggleFlyTarget")); }
    public List<String> heal() { return toStringList(config.get("heal")); }
    public List<String> healOther() { return toStringList(config.get("healOther")); }
    public List<String> feed() { return toStringList(config.get("feed")); }
    public List<String> feedOther() { return toStringList(config.get("feedOther")); }
    public List<String> healTarget() { return toStringList(config.get("healTarget")); }
    public List<String> day() { return toStringList(config.get("day")); }
    public List<String> dayWorld() { return toStringList(config.get("dayWorld")); }
    public List<String> night() { return toStringList(config.get("night")); }
    public List<String> nightWorld() { return toStringList(config.get("nightWorld")); }
    public List<String> morning() { return toStringList(config.get("morning")); }
    public List<String> morningWorld() { return toStringList(config.get("morningWorld")); }
    public List<String> tpHere() { return toStringList(config.get("tpHere")); }
    public List<String> tpHereTarget() { return toStringList(config.get("tpHereTarget")); }
    public List<String> tp() { return toStringList(config.get("tp")); }
    public List<String> setSpawn() { return toStringList(config.get("setSpawn")); }
    // TPA Commands
    public List<String> tpa() { return toStringList(config.get("teleportRequest.tpa")); }
    public List<String> tpaTarget() { return toStringList(config.get("teleportRequest.tpaTarget")); }
    public List<String> tpaSuccess() { return toStringList(config.get("teleportRequest.tpaSuccess")); }
    public List<String> tpaSuccessTarget() { return toStringList(config.get("teleportRequest.tpaSuccessTarget")); }
    public List<String> tpaDenied() { return toStringList(config.get("teleportRequest.tpaDenied")); }
    public List<String> tpaDeniedTarget() { return toStringList(config.get("teleportRequest.tpaDeniedTarget")); }
    public List<String> tpaPending() { return toStringList(config.get("teleportRequest.tpaPending")); }
    public List<String> tpaPendingTarget() { return toStringList(config.get("teleportRequest.tpaPendingTarget")); }

    public List<String> replace(List<String> message, String key, String value) {
        return message.stream()
                .map(str -> str.replace(key, value))
                .collect(Collectors.toList());
    }

    private List<String> toStringList(Object input) {
        if (input instanceof String str) {
            return Collections.singletonList(str);
        } else if (input instanceof List<?> list) {
            return list.stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        } else {
            LightCore.instance.getConsolePrinter().printError(List.of(
                    "Error in your message file at " + input,
                    "Input must be a String or a List of Strings",
                    "example as String: test: 'Test message'",
                    "example as List: test: - 'Test message'"
            ));
            throw new IllegalArgumentException("Input must be a String or a List of Strings");
        }
    }
}