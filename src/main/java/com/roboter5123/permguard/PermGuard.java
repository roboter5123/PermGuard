package com.roboter5123.permguard;

import com.roboter5123.permguard.listener.JoinListener;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PermGuard extends JavaPlugin {

    private RegisteredServiceProvider<LuckPerms> luckPermsProvider;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger logger = getLogger();
        this.luckPermsProvider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (luckPermsProvider == null){
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        LuckPerms luckPerms = luckPermsProvider.getProvider();
        logger.info("Initializing PermGuard");
        getServer().getPluginManager().registerEvents(new JoinListener(luckPerms, logger), this);
        logger.info("Finished initializing PermGuard");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger logger = getLogger();
        if (luckPermsProvider == null){
            logger.info("Plugin disabled! You do not have LuckPerms installed.");
        }
    }
}
