package com.beanbeanjuice.antifarm2.antifarm;

import com.beanbeanjuice.antifarm2.AntiFarm2;
import com.beanbeanjuice.antifarm2.configuration.Configuration;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent.ChangeReason;

public class AntiVillagerCareer implements Listener {

	private final Configuration config;

	public AntiVillagerCareer(AntiFarm2 plugin) {
		this.config = plugin.getConfig();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onVillagerCareerChange(VillagerCareerChangeEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getEntity().getWorld().getName())) return;

		if (event.isCancelled() || event.getEntity() == null || event.getReason() == null) return;
		if (!event.getReason().equals(ChangeReason.EMPLOYED)) return;
		if (!config.getBoolean("villager-settings.prevent-villagers-profession-change", true)) return;

		Villager villager = event.getEntity();
		villager.setVillagerExperience(villager.getVillagerExperience() + 1);

	}

}
