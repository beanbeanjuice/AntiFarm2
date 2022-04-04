package antiFarm;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public static Plugin plugin;
	
	public void onEnable() {
		plugin = this;
		registerEvents(this, new AntiPistonFarm(), new AntiVillagerFarm(), new AntiWaterFarm(), new AntiCactusFarm(), new AntiEndermanFarm(), new AntiVillagerBreed(), new AntiMobFarm(), new AntiLightlessFarm(), new AntiBoneMeal(), new AntiFishFarm(), new AntiWaterlessFarm(), new AntiMobSpawner(), new AntiVillagerTransform(), new AntiVillagerTarget(), new AntiVillageGuard());
		getCommand("antifarm").setExecutor(new Commands());
		J.configJ.createConfig();
	
	public void onDisable() {
		plugin = null;
	}
	
	public static void registerEvents(Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, plugin);
		}
	}
	
}
