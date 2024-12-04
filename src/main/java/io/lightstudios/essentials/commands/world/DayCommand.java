package io.lightstudios.essentials.commands.world;

import io.lightstudios.core.LightCore;
import io.lightstudios.core.util.LightSounds;
import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.essentials.LightEssentials;
import io.lightstudios.essentials.permissions.LightPermissions;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.generator.WorldInfo;

import java.util.List;

public class DayCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of();
    }

    @Override
    public String getDescription() {
        return "Set the time to day for a world";
    }

    @Override
    public String getSyntax() {
        return "/day <world>";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public String getPermission() {
        return LightPermissions.DAY_COMMAND.getPerm();
    }

    @Override
    public TabCompleter registerTabCompleter() {
        return (sender, command, alias, args) -> {
            if (args.length == 1) {
                return Bukkit.getServer().getWorlds().stream().map(WorldInfo::getName).toList();
            }
            return null;
        };
    }

    @Override
    public boolean performAsPlayer(Player player, String[] strings) {

        if(strings.length == 0) {
            player.getWorld().setTime(4000);
            LightCore.instance.getMessageSender().sendPlayerMessage(
                    player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().day());
            LightSounds.onSuccess(player);
            return true;
        }

        if(strings.length == 1) {
            World world = Bukkit.getWorld(strings[0]);

            if(world == null) {
                LightCore.instance.getMessageSender().sendPlayerMessage(
                        player,
                        LightEssentials.messagePrefix + LightEssentials.instance.getMessages().worldNotFound()
                        .replace("#world#", strings[0]));
                return false;
            }

            world.setTime(4000);
            LightCore.instance.getMessageSender().sendPlayerMessage(
                    player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().dayWorld()
                            .replace("#world#", strings[0]));
            LightSounds.onSuccess(player);
            return false;
        }

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}
