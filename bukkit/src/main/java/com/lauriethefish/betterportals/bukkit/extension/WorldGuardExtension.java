package com.lauriethefish.betterportals.bukkit.extension;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class WorldGuardExtension {
    private static StateFlag BETTER_PORTALS_SPAWN_FLAG;

    public static void registerFlags() throws NoClassDefFoundError {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            StateFlag flag = new StateFlag("better-portals-spawn", true);
            registry.register(flag);
            BETTER_PORTALS_SPAWN_FLAG = flag;
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("better-portals-spawn");
            if (existing instanceof StateFlag) {
                BETTER_PORTALS_SPAWN_FLAG = (StateFlag) existing;
            }
        }
    }

    public static boolean canSpawnPortalInRegion(@NotNull Location loc, Player player) {
        WorldGuardPlatform platform = WorldGuard.getInstance().getPlatform();

        //Can't wrap null
        LocalPlayer wgPlayer = player == null ? null : WorldGuardPlugin.inst().wrapPlayer(player);

        //Bypass requires WG player instance
        if(wgPlayer != null) {
            boolean canBypass = platform.getSessionManager().hasBypass(wgPlayer, wgPlayer.getWorld());
            if(canBypass) {
                return true;
            }
        }

        //Testing flags doesn't require player instance
        return platform
                .getRegionContainer()
                .createQuery()
                .testState(BukkitAdapter.adapt(loc), wgPlayer, BETTER_PORTALS_SPAWN_FLAG);
    }
}
