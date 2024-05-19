package com.beanbeanjuice.antifarm2.antifarm;

import com.beanbeanjuice.antifarm2.AntiFarm2;
import com.beanbeanjuice.antifarm2.configuration.Configuration;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockShearEntityEvent;

public class AntiDispenser implements Listener {

	private final Configuration config;

	public AntiDispenser(AntiFarm2 plugin) {
		this.config = plugin.getConfig();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onDispense(BlockDispenseEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getBlock().getWorld().getName())) return;

		if (event.isCancelled() || event.getBlock() == null || event.getItem() == null || event.getVelocity() == null || !event.getBlock().getType().equals(Material.DISPENSER)) return;
		if (!config.getBoolean("anti-dispenser.enable") || !config.getStringList("anti-dispenser.blocked-item-list").contains(event.getItem().getType().toString().toUpperCase())) return;

		Dispenser dispenser = (Dispenser) event.getBlock().getBlockData();
		Block block = event.getBlock().getRelative(dispenser.getFacing());

		if (!config.getStringList("farm-blocks").contains(block.getType().toString().toUpperCase())) return;

		event.setCancelled(true);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onBlockShearEntity(BlockShearEntityEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getBlock().getWorld().getName())) return;

		if (event.isCancelled() || event.getEntity() == null || !event.getEntity().getType().equals(EntityType.SHEEP)) return;
		if (!config.getBoolean("anti-dispenser.enable") || !config.getBoolean("anti-dispenser.prevent-shearing", true)) return;

		event.setCancelled(true);

	}

}
