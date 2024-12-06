package io.lightstudios.essentials.cache;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class PlayerCache {

    private final List<UUID> teleportRequests;
    private final List<UUID> teleportQueue;

    public PlayerCache() {
        this.teleportRequests = new ArrayList<>();
        this.teleportQueue = new ArrayList<>();
    }

}
