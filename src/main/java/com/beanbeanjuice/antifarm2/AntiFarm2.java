package com.beanbeanjuice.antifarm2;

import com.beanbeanjuice.antifarm2.antifarm.*;
import com.beanbeanjuice.antifarm2.configuration.Configuration;
import com.beanbeanjuice.antifarm2.core.Commands;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiFarm2 extends JavaPlugin {

    private Configuration config;
    private Configuration spawners;

    @Override
    public void onEnable() {
        // Plugin startup logic
        config = new Configuration("config", this);
        spawners = new Configuration("spawners", this);

        registerEvents(this,
                new AntiPistonFarm(this),
                new AntiVillagerFarm(this),
                new AntiWaterFarm(this),
                new AntiCactusFarm(this),
                new AntiEndermanFarm(this),
                new AntiVillagerBreed(this),
                new AntiMobFarm(this),
                new AntiLightlessFarm(this),
                new AntiDispenser(this),
                new AntiFishFarm(this),
                new AntiWaterlessFarm(this),
                new AntiMobSpawner(this),
//                new AntiVillagerTransform(this),
                new AntiVillagerTarget(this),
                new AntiVillageGuard(this),
                new AntiSnowballFarm(this),
                new AntiRaidFarm(this),
                new AntiBerryFarm(this),
                new AntiZeroTickFarm(this),
                new AntiFroglightFarm(this),
                new AntiVillagerCareer(this),
                new AntiVillagerTrade(this),
                new AntiStringDupe(this),
                new AntiChickenEggFarm(this),
                new AntiCowMilk(this),
                new AntiDripstoneFarm(this),
                new AntiLavaFarm(this));

        getCommand("antifarm").setExecutor(new Commands(this));
    }

    private static void registerEvents(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }

    @Override
    public Configuration getConfig() {
        return config;
    }

    public Configuration getSpawners() {
        return spawners;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
