package com.beanbeanjuice.antifarm2.configuration;

import java.io.File;

import com.beanbeanjuice.antifarm2.AntiFarm2;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration extends YamlConfiguration {

    private final AntiFarm2 plugin;
    private File file;

    public Configuration(String path, AntiFarm2 plugin) {
        this.plugin = plugin;
        this.load(path);
    }

    public void saveDefaults(String path) {
        this.plugin.saveResource(path + ".yml", false);
    }

    @Override
    public void load(String path) {

        this.file = new File(plugin.getDataFolder(), path + ".yml");

        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.saveDefaults(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            if (path.equalsIgnoreCase("config")) {
                ConfigUpdater.update(plugin, path + ".yml", this.file);
            }
            super.load(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void save() {
        try {
            super.save(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload(String path) {
        this.load(path);
    }

}
