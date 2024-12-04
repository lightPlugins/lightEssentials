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

public class FlyCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of();
    }

    @Override
    public String getDescription() {
        return "Toggle fly mode";
    }

    @Override
    public String getSyntax() {
        return "/fly <playername>";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public String getPermission() {
        return LightPermissions.FLY_COMMAND.getPerm();
    }

    @Override
    public TabCompleter registerTabCompleter() {
        return (sender, command, alias, args) -> {
            if (args.length == 1) {
                return Bukkit.getServer().getOnlinePlayers().stream().map(Player::getName).toList();
            }
            return null;
        };
    }

    @Override
    public boolean performAsPlayer(Player player, String[] args) {
        if(args.length == 0) {

            String state = setFly(player);

            LightCore.instance.getMessageSender().sendPlayerMessage(
                    player,
            LightEssentials.messagePrefix + LightEssentials.instance.getMessages().toggleFly()
                    .replace("#state#", state));
            LightSounds.onSuccess(player);
            return true;
        }

        if(args.length == 1) {
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if(target == null) {
                LightCore.instance.getMessageSender().sendPlayerMessage(
                        player,
                        LightEssentials.messagePrefix + LightEssentials.instance.getMessages().playerNotFound()
                                .replace("#player#", args[0]));
                LightSounds.onFail(player);
                return false;
            }

            String state = setFly(target);

            LightCore.instance.getMessageSender().sendPlayerMessage(
                    player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().toggleFlyOther()
                            .replace("#state#", state)
                            .replace("#player#", target.getName()));

            LightCore.instance.getMessageSender().sendPlayerMessage(
                    target,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().toggleFlyTarget()
                            .replace("#state#", state)
                            .replace("#player#", player.getName()));

            LightSounds.onSuccess(player);
            LightSounds.onSuccess(target);
        }

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }

    private String setFly(Player player) {
        if(player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            return "Off";
        }

        player.setAllowFlight(true);
        player.setFlying(true);
        return "On";
    }
}
