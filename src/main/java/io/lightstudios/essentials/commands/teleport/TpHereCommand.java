package io.lightstudios.essentials.commands.teleport;

import io.lightstudios.core.LightCore;
import io.lightstudios.core.util.LightSounds;
import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.essentials.LightEssentials;
import io.lightstudios.essentials.permissions.LightPermissions;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class TpHereCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of();
    }

    @Override
    public String getDescription() {
        return "Teleport a player to you";
    }

    @Override
    public String getSyntax() {
        return "/tphere <player>";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public String getPermission() {
        return LightPermissions.TPHERE_COMMAND.getPerm();
    }

    @Override
    public TabCompleter registerTabCompleter() {
        return (sender, command, alias, args) -> {

            if(args.length == 1) {
                return Bukkit.getServer().getOnlinePlayers().stream().map(Player::getName).toList();
            }

            return null;
        };
    }

    @Override
    public boolean performAsPlayer(Player player, String[] args) {

        if(args.length != 1) {
            LightCore.instance.getMessageSender().sendPlayerMessage(player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().wrongSyntax()
                            .replace("#syntax#", getSyntax()));
            return false;
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);

        if(target == null) {
            LightCore.instance.getMessageSender().sendPlayerMessage(
                    player,
                   LightEssentials.messagePrefix + LightEssentials.instance.getMessages().playerNotFound()
                            .replace("#player#", args[0])
            );
            return false;
        }

        target.teleport(player);

        LightCore.instance.getMessageSender().sendPlayerMessage(
                player,
                LightEssentials.messagePrefix + LightEssentials.instance.getMessages().tpHere()
                        .replace("#player#", target.getName())
        );

        LightCore.instance.getMessageSender().sendPlayerMessage(
                target,
                LightEssentials.messagePrefix + LightEssentials.instance.getMessages().tpHereTarget()
                        .replace("#player#", player.getName())
        );

        LightSounds.onSuccess(player);
        LightSounds.onSuccess(target);

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}
