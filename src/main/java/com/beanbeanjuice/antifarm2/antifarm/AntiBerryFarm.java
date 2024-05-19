package com.beanbeanjuice.antifarm2.antifarm;

import com.beanbeanjuice.antifarm2.AntiFarm2;
import com.beanbeanjuice.antifarm2.configuration.Configuration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;

public class AntiBerryFarm implements Listener {

	private final Configuration config;

	public AntiBerryFarm(AntiFarm2 plugin) {
		this.config = plugin.getConfig();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onEntityChangeBlock(EntityChangeBlockEvent event) {

		if (config.getStringList("settings.disabled-worlds").contains(event.getBlock().getWorld().getName())) return;

		if (event.isCancelled() || event.getBlock() == null || event.getEntity() == null) return;
		if (!event.getEntity().getType().equals(EntityType.FOX)) return;
		if (!event.getBlock().getType().equals(Material.SWEET_BERRY_BUSH)) return;
		if (!config.getBoolean("farms-settings.prevent-berry-farms", true)) return;

		event.setCancelled(true);

		event.getEntity().getWorld().playSound(event.getEntity(), Sound.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, 1.0f, 1.0f);
		Ageable sweetBerryBush = (Ageable) event.getBlock().getBlockData();
		sweetBerryBush.setAge(1);
		event.getBlock().setBlockData(sweetBerryBush);

		LivingEntity fox = (LivingEntity) event.getEntity();

		if (!fox.getEquipment().getItemInMainHand().getType().equals(Material.AIR)) return;

		fox.getEquipment().setItemInMainHand(new ItemStack(Material.SWEET_BERRIES, 1));

	}

}
