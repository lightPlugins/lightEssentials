package io.lightstudios.essentials.commands.util;

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

public class FeedCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of();
    }

    @Override
    public String getDescription() {
        return "Feed yourself";
    }

    @Override
    public String getSyntax() {
        return "/feed <playername>";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public String getPermission() {
        return LightPermissions.FEED_COMMAND.getPerm();
    }

    @Override
    public TabCompleter registerTabCompleter() {
        return (sender, command, alias, args) ->  {
            if(args.length == 1) {
                return Bukkit.getServer().getOnlinePlayers().stream().map(Player::getName).toList();
            }
            return null;
        };
    }

    @Override
    public boolean performAsPlayer(Player player, String[] args) {

        if(args.length == 0) {
            player.setFoodLevel(20);
            LightCore.instance.getMessageSender().sendPlayerMessage(player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().feed());
            LightSounds.onSuccess(player);
            return true;
        }

        if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                LightCore.instance.getMessageSender().sendPlayerMessage(player,
                        LightEssentials.messagePrefix + LightEssentials.instance.getMessages().playerNotFound());
                LightSounds.onFail(player);
                return false;
            }
            target.setFoodLevel(20);
            LightCore.instance.getMessageSender().sendPlayerMessage(player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().feedOther()
                            .stream().map(s -> s
                                    .replace("#player#", target.getName()))
                            .toList());
            LightSounds.onSuccess(player);
            return true;
        }

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}
