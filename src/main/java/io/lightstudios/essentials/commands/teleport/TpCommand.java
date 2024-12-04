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

public class TpCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of();
    }

    @Override
    public String getDescription() {
        return "Teleport to a player";
    }

    @Override
    public String getSyntax() {
        return "/tp <player>";
    }

    @Override
    public int maxArgs() {
        return 2;
    }

    @Override
    public String getPermission() {
        return LightPermissions.TP_COMMAND.getPerm();
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
    public boolean performAsPlayer(Player player, String[] args) {

        if(args.length != 1) {
            player.sendMessage("§cInvalid usage! Correct usage: §7" + getSyntax());
            LightCore.instance.getMessageSender().sendPlayerMessage(player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().wrongSyntax()
                            .replace("#syntax#", getSyntax()));
            LightSounds.onFail(player);
            return false;
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);

        if(target == null) {
            LightCore.instance.getMessageSender().sendPlayerMessage(player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().playerNotFound()
                            .replace("#player#", args[0]));
            LightSounds.onFail(player);
            return false;
        }

        player.teleport(target);

        LightCore.instance.getMessageSender().sendPlayerMessage(player,
                LightEssentials.messagePrefix + LightEssentials.instance.getMessages().tp()
                        .replace("#player#", target.getName()));
        LightSounds.onSuccess(player);

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}
