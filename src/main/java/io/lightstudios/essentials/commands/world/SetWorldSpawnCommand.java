package io.lightstudios.essentials.commands.world;

import io.lightstudios.core.LightCore;
import io.lightstudios.core.util.LightSounds;
import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.essentials.LightEssentials;
import io.lightstudios.essentials.permissions.LightPermissions;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class SetWorldSpawnCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of("onworldjoin", "ondeath", "onserverjoin");
    }

    @Override
    public String getDescription() {
        return "Set the spawn location";
    }

    @Override
    public String getSyntax() {
        return "/setspawn <spawntype>";
    }

    @Override
    public int maxArgs() {
        return 0;
    }

    @Override
    public String getPermission() {
        return LightPermissions.SET_SPAWN_COMMAND.getPerm();
    }

    @Override
    public TabCompleter registerTabCompleter() {
        return null;
    }

    @Override
    public boolean performAsPlayer(Player player, String[] strings) {

        World world = player.getWorld();
        String worldName = world.getName();
        Location location = player.getLocation();

        world.setSpawnLocation(location);

        LightCore.instance.getMessageSender().sendPlayerMessage(player,
                LightEssentials.messagePrefix + LightEssentials.instance.getMessages().setSpawn()
                        .stream().map(s -> s
                                .replace("#world#", worldName))
                        .toList());
        LightSounds.onSuccess(player);

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}
