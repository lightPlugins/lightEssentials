package io.lightstudios.essentials.permissions;

import lombok.Getter;

@Getter
public enum LightPermissions {


    /*
        Admin Command Perissions
     */

    CREATIVE_COMMAND("lightessentials.command.admin.creative"),
    SURVIVAL_COMMAND("lightessentials.command.admin.survival"),
    ADVENTURE_COMMAND("lightessentials.command.admin.adventure"),
    SPECTATOR_COMMAND("lightessentials.command.admin.spectator"),
    SPEED_COMMAND("lightessentials.command.admin.speed"),
    FLY_COMMAND("lightessentials.command.admin.fly"),
    HEAL_COMMAND("lightessentials.command.admin.heal"),
    DAY_COMMAND("lightessentials.command.admin.day"),
    NIGHT_COMMAND("lightessentials.command.admin.night"),
    TPHERE_COMMAND("lightessentials.command.admin.tphere"),
    TP_COMMAND("lightessentials.command.admin.tp"),
            ;

    private final String perm;
    LightPermissions(String perm) { this.perm = perm; }
}
