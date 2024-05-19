package com.beanbeanjuice.antifarm2.antifarm;

import com.beanbeanjuice.antifarm2.AntiFarm2;
import com.beanbeanjuice.antifarm2.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class AntiVillageGuard implements Listener {

	private final Configuration config;

	public AntiVillageGuard(AntiFarm2 plugin) {
		this.config = plugin.getConfig();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onCreatureSpawn(CreatureSpawnEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getEntity().getWorld().getName())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.IRON_GOLEM)) return;

		if (event.getSpawnReason().equals(SpawnReason.VILLAGE_DEFENSE)) {

			if (!config.getBoolean("villager-settings.prevent-golem-spawning.village-defense", true)) return;

			event.setCancelled(true);

		} else if (event.getSpawnReason().equals(SpawnReason.VILLAGE_INVASION)) {

			if (!config.getBoolean("villager-settings.prevent-golem-spawning.village-raids", true)) return;

			event.setCancelled(true);

		}

	}

}
