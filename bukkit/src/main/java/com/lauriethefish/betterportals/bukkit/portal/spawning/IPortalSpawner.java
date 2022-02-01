package com.lauriethefish.betterportals.bukkit.portal.spawning;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface IPortalSpawner {
    /**
     * Schedules a portal spawn to happen. This could take a number of ticks as finding the destination takes time
     * @param originPosition The position of the portal that was lit
     * @param size The size of the lit portal
     * @param onFinish Called with the resultant position when finished
     * @param creatingPlayer Player which creates portal
     * @return true once the spawn has started, false if no valid WorldLink was found.
     */
    boolean findAndSpawnDestination(@NotNull Location originPosition, @NotNull Vector size, Player creatingPlayer, Consumer<PortalSpawnPosition> onFinish);
}
