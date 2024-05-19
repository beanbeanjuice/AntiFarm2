package com.beanbeanjuice.antifarm2.antifarm;

import com.beanbeanjuice.antifarm2.AntiFarm2;
import com.beanbeanjuice.antifarm2.configuration.Configuration;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;

public class AntiSnowballFarm implements Listener {

	private final Configuration config;

	public AntiSnowballFarm(AntiFarm2 plugin) {
		this.config = plugin.getConfig();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityBlockForm(EntityBlockFormEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getBlock().getWorld().getName())) return;

		if (event.isCancelled() || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.SNOWMAN)) return;
		if (!event.getNewState().getType().equals(Material.SNOW)) return;
		if (!config.getBoolean("farms-settings.prevent-snowball-farms", true)) return;

		event.setCancelled(true);

	}

}
