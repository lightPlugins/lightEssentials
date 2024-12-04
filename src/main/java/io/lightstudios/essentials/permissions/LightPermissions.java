package io.lightstudios.essentials.permissions;

import lombok.Getter;

@Getter
public enum LightPermissions {


    /*
        Admin Command Perissions
     */

    CREATIVE_COMMAND("lightessentials.command.admin.creative"),
            ;

    private final String perm;
    LightPermissions(String perm) { this.perm = perm; }
}
