package io.lightstudios.essentials.commands.util;

import io.lightstudios.core.LightCore;
import io.lightstudios.core.util.LightSounds;
import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.essentials.LightEssentials;
import io.lightstudios.essentials.permissions.LightPermissions;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class SpeedCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of();
    }

    @Override
    public String getDescription() {
        return "Change the walk fly and swim speed of a player";
    }

    @Override
    public String getSyntax() {
        return "/speed <speed> <target>";
    }

    @Override
    public int maxArgs() {
        return 2;
    }

    @Override
    public String getPermission() {
        return LightPermissions.SPEED_COMMAND.getPerm();
    }

    @Override
    public TabCompleter registerTabCompleter() {
        return (sender, command, alias, args) -> {

            if(args.length == 1) {
                return List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            }

            if (args.length == 2) {
                return Bukkit.getServer().getOnlinePlayers().stream().map(Player::getName).toList();
            }
            return null;
        };
    }

    @Override
    public boolean performAsPlayer(Player player, String[] args) {

        if(args.length >= 1) {

            double speed = Double.parseDouble(args[0]);

            if(speed < 0.0 || speed > 10.0) {
                LightCore.instance.getMessageSender().sendPlayerMessage(
                        player,
                        LightEssentials.messagePrefix + LightEssentials.instance.getMessages().maxOrMinSpeed());
                LightSounds.onFail(player);
                return false;
            }

            if(args.length == 2) {

                Player target = Bukkit.getServer().getPlayer(args[1]);
                if(target == null) {
                    LightCore.instance.getMessageSender().sendPlayerMessage(
                            player,
                            LightEssentials.messagePrefix + LightEssentials.instance.getMessages().playerNotFound()
                    );
                    LightSounds.onFail(player);
                    return false;
                }

                target.setWalkSpeed((float) scale(speed));
                target.setFlySpeed((float) scale(speed));

                LightCore.instance.getMessageSender().sendPlayerMessage(
                        player,
                        LightEssentials.instance.getMessages().changeSpeedOther()
                                .replace("#speed#", String.valueOf(speed))
                                .replace("#player#", target.getName())
                );

                LightCore.instance.getMessageSender().sendPlayerMessage(
                        target,
                        LightEssentials.instance.getMessages().changeSpeedTarget()
                                .replace("#speed#", String.valueOf(speed))
                                .replace("#player#", player.getName())
                );

                LightSounds.onSuccess(player);
                LightSounds.onSuccess(target);
                return false;

            }

            player.setWalkSpeed((float) scale(speed));
            player.setFlySpeed((float) scale(speed));

            LightCore.instance.getMessageSender().sendPlayerMessage(
                    player,
                    LightEssentials.instance.getMessages().changeSpeed()
                            .replace("#speed#", String.valueOf(speed))
            );
            LightSounds.onSuccess(player);

        }

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }


    private double scale(double value) {

        // set default movement speed
        if(value == 1) {
            return 0.2;
        }
        // set min movement speed
        if(value == 0) {
            return 0.0;
        }
        // set max movement speed
        if(value == 10) {
            return 1.0;
        }

        // Original range
        double originalMin = 0.0;
        double originalMax = 10.0;

        // Target range
        double targetMin = 0.1;
        double targetMax = 1.0;

        // Scale factor
        double scaleFactor = (targetMax - targetMin) / (originalMax - originalMin);

        // Apply scaling
        return targetMin + (value - originalMin) * scaleFactor;
    }
}
