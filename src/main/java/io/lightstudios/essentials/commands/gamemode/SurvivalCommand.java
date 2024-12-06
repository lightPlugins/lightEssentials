package io.lightstudios.essentials.commands.gamemode;

import io.lightstudios.core.LightCore;
import io.lightstudios.core.util.LightSounds;
import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.essentials.LightEssentials;
import io.lightstudios.essentials.permissions.LightPermissions;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class SurvivalCommand implements LightCommand {

    @Override
    public List<String> getSubcommand() {
        return List.of();
    }

    @Override
    public String getDescription() {
        return "Change your game mode to survival";
    }

    @Override
    public String getSyntax() {
        return "/gms <player>";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public String getPermission() {
        return LightPermissions.SURVIVAL_COMMAND.getPerm();
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
        if(args.length == 0) {
            player.setGameMode(GameMode.SURVIVAL);
            LightCore.instance.getMessageSender().sendPlayerMessage(
                    player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().gameModeChange()
                            .stream().map(s -> s
                                    .replace("#gamemode#", "Survival"))
                            .toList());
            LightSounds.onSuccess(player);
            return false;
        }

        if (args.length == 1) {
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if(target == null) {
                LightCore.instance.getMessageSender().sendPlayerMessage(
                        player,
                        LightEssentials.messagePrefix + LightEssentials.instance.getMessages().playerNotFound()
                                .stream().map(s -> s
                                        .replace("#player#", args[0]))
                                .toList());
                LightSounds.onFail(player);
                return false;
            }

            target.setGameMode(GameMode.SURVIVAL);
            LightCore.instance.getMessageSender().sendPlayerMessage(
                    player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().gameModeChangeOther()
                            .stream().map(s -> s
                                    .replace("#gamemode#", "Survival")
                                    .replace("#player#", target.getName()))
                            .toList());
            LightCore.instance.getMessageSender().sendPlayerMessage(
                    target,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().gameModeChangeTarget()
                            .stream().map(s -> s
                                    .replace("#gamemode#", "Survival")
                                    .replace("#player#", player.getName()))
                            .toList());
            LightSounds.onSuccess(player);
            LightSounds.onSuccess(target);
            return false;
        }

        return false;

    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}
