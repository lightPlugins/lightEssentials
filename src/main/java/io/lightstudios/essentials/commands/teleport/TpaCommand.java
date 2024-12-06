package io.lightstudios.essentials.commands.teleport;

import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.essentials.LightEssentials;
import io.lightstudios.essentials.permissions.LightPermissions;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class TpaCommand implements LightCommand {

    @Override
    public List<String> getSubcommand() {
        return List.of();
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public String getPermission() {
        return LightPermissions.TPA_COMMAND.getPerm();
    }

    @Override
    public TabCompleter registerTabCompleter() {
        return (commandSender, command, alias, args) -> {

            if(args.length == 1) {
                return Bukkit.getServer().getOnlinePlayers().stream().map(Player::getName).toList();
            }

            return null;
        };
    }

    @Override
    public boolean performAsPlayer(Player player, String[] strings) {

        if(LightEssentials.instance.getPlayerCache().getTeleportRequests().contains(player.getUniqueId())) {
            player.sendMessage("§cYou already have a pending teleport request!");
            return false;
        }

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}
