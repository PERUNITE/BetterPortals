package com.lauriethefish.betterportals.bukkit.extension;

import org.bukkit.Bukkit;

public final class ExtensionHelper {
    public static boolean isPluginLoaded(String pluginName) {
        if(pluginName == null) return false;
        return Bukkit.getPluginManager().getPlugin(pluginName) != null;
    }
}
