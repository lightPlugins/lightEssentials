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

public class HealCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of();
    }

    @Override
    public String getDescription() {
        return "Heal yourself";
    }

    @Override
    public String getSyntax() {
        return "/heal <playername>";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public String getPermission() {
        return LightPermissions.HEAL_COMMAND.getPerm();
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

            setHealth(player);
            LightCore.instance.getMessageSender().sendPlayerMessage(player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().heal());
            LightSounds.onSuccess(player);
            return true;

        }

        if(args.length == 1) {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if(target == null) {
                LightCore.instance.getMessageSender().sendPlayerMessage(player,
                        LightEssentials.messagePrefix + LightEssentials.instance.getMessages().playerNotFound()
                                .stream().map(s -> s
                                        .replace("#player#", args[0]))
                                .toList());
                LightSounds.onFail(player);
                return false;
            }
            setHealth(target);
            LightCore.instance.getMessageSender().sendPlayerMessage(player,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().healOther()
                            .stream().map(s -> s
                                    .replace("#player#", target.getName()))
                            .toList());
            LightCore.instance.getMessageSender().sendPlayerMessage(target,
                    LightEssentials.messagePrefix + LightEssentials.instance.getMessages().healTarget()
                            .stream().map(s -> s
                                    .replace("#player#", player.getName()))
                            .toList());
            LightSounds.onSuccess(player);
            LightSounds.onSuccess(target);
            return true;
        }

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }

    private void setHealth(Player player) {
        // supports more than 20 health example:
        // - from a rpg plugin (player has more than 20 health)
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setSaturation(20);
    }
}
