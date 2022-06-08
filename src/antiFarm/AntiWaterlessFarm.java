package antiFarm;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import core.J;

public class AntiWaterlessFarm implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.isCancelled()) return;
		if (event.getBlock() == null) return;
		if (event.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.FARMLAND)) {
			BlockData blockData = event.getBlock().getRelative(BlockFace.DOWN).getBlockData();
			Farmland farmland = (Farmland) blockData;
			if (farmland.getMoisture() == 0) {
				if (J.configJ.config.getBoolean("prevent-farms.waterless-farms", true)) {
					if (J.configJ.config.getStringList("farm-blocks").contains(event.getBlock().getType().toString().toUpperCase())) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() == null) return;
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (event.getItem().getType().equals(Material.BONE_MEAL)) {
				if (event.getClickedBlock().getRelative(BlockFace.DOWN).getType().equals(Material.FARMLAND)) {
					BlockData blockData = event.getClickedBlock().getRelative(BlockFace.DOWN).getBlockData();
					Farmland farmland = (Farmland) blockData;
					if (farmland.getMoisture() == 0) {
						if (J.configJ.config.getBoolean("prevent-farms.waterless-farms", true)) {
							if (J.configJ.config.getStringList("farm-blocks").contains(event.getClickedBlock().getType().toString().toUpperCase())) {
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockGrow(BlockGrowEvent event) {
		if (event.isCancelled()) return;
		if (event.getBlock() == null) return;
		if (event.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.FARMLAND)) {
			BlockData blockData = event.getBlock().getRelative(BlockFace.DOWN).getBlockData();
			Farmland farmland = (Farmland) blockData;
			if (farmland.getMoisture() == 0) {
				if (J.configJ.config.getBoolean("prevent-farms.waterless-farms", true)) {
					if (J.configJ.config.getStringList("farm-blocks").contains(event.getBlock().getType().toString().toUpperCase())) {
						event.setCancelled(true);
						event.getBlock().breakNaturally();
						event.getBlock().setType(Material.AIR);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDispense(BlockDispenseEvent event) {
		if (event.isCancelled()) return;
		if (event.getItem() == null) return;
		if (event.getBlock().getType().equals(Material.DISPENSER)) {
			if (event.getItem().getType().equals(Material.BONE_MEAL)) {
				Dispenser dispenser = (Dispenser) event.getBlock().getBlockData();
				Block block = event.getBlock().getRelative(dispenser.getFacing());
				if (block.getRelative(BlockFace.DOWN).getType().equals(Material.FARMLAND)) {
					Farmland farmland = (Farmland) block.getRelative(BlockFace.DOWN).getBlockData();
					if (farmland.getMoisture() == 0) {
						if (J.configJ.config.getBoolean("prevent-farms.waterless-farms", true)) {
							if (J.configJ.config.getStringList("farm-blocks").contains(block.getType().toString().toUpperCase())) {
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
	
}