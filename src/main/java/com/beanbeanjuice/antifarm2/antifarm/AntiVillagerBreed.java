package com.beanbeanjuice.antifarm2.antifarm;

import com.beanbeanjuice.antifarm2.AntiFarm2;
import com.beanbeanjuice.antifarm2.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class AntiVillagerBreed implements Listener {

	private final Configuration config;

	public AntiVillagerBreed(AntiFarm2 plugin) {
		this.config = plugin.getConfig();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onVillagerPickup(EntityPickupItemEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getEntity().getWorld().getName())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.VILLAGER)) return;
		if (!config.getBoolean("villager-settings.prevent-villagers-breed", true)) return;

		Villager villager = (Villager) event.getEntity();
		villager.setBreed(false);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onVillagerDrop(EntityDropItemEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getEntity().getWorld().getName())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.VILLAGER)) return;
		if (!config.getBoolean("villager-settings.prevent-villagers-breed", true)) return;

		Villager villager = (Villager) event.getEntity();
		villager.setBreed(false);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onVillagerBreed(EntityBreedEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getEntity().getWorld().getName())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.VILLAGER)) return;
		if (event.getMother() == null || event.getFather() == null) return;
		if (!config.getBoolean("villager-settings.prevent-villagers-breed", true)) return;

		event.setCancelled(true);

		Villager mother = (Villager) event.getMother();
		Villager father = (Villager) event.getFather();

		mother.setBreed(false);
		father.setBreed(false);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onVillagerSpawn(CreatureSpawnEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getEntity().getWorld().getName())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.VILLAGER)) return;
		if (!event.getSpawnReason().equals(SpawnReason.BREEDING)) return;
		if (!config.getBoolean("villager-settings.prevent-villagers-breed", true)) return;

		event.setCancelled(true);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityEnterLoveMode(EntityEnterLoveModeEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getEntity().getWorld().getName())) return;

		if (event.isCancelled() || event.getEntity() == null || !config.getBoolean("villager-settings.prevent-villagers-breed", true) || !event.getEntity().getType().equals(EntityType.VILLAGER)) return;

		Villager villager = (Villager) event.getEntity();
		villager.setBreed(false);

	}

}
