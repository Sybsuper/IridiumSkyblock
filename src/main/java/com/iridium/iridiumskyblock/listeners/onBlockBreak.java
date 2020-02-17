package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.*;
import com.iridium.iridiumskyblock.configs.Missions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Crops;

public class onBlockBreak implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        try {
            if (e.isCancelled()) return;
            User u = User.getUser(e.getPlayer());
            Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getBlock().getLocation());
            if (island != null) {
                if (u.islandID == island.getId()) {
                    for (String mission : IridiumSkyblock.getMissions().mission.keySet()) {
                        if (!island.missionLevels.containsKey(mission)) island.missionLevels.put(mission, 1);
                        Missions.Mission m = IridiumSkyblock.getMissions().mission.get(mission).get(island.missionLevels.get(mission));
                        if (m.type == MissionType.BLOCK_BREAK) {
                            if (m.conditions.isEmpty() || m.conditions.contains(MultiversionMaterials.fromMaterial(e.getBlock().getType()).toString()) || (e.getBlock().getState().getData() instanceof Crops && m.conditions.contains(((Crops) e.getBlock().getState().getData()).getState().toString()))) {
                                island.addMission(mission, 1);
                            }
                        }
                    }
                }
                island.blocks.remove(e.getBlock().getLocation());
                island.calculateIslandValue();
                if ((!island.getPermissions((u.islandID == island.getId() || island.isCoop(u.getIsland())) ? (island.isCoop(u.getIsland()) ? Role.Member : u.getRole()) : Role.Visitor).breakBlocks) && !u.bypassing)
                    e.setCancelled(true);
            } else {
                if (e.getBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                    if (!u.bypassing) {
                        e.setCancelled(true);
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMonitorBreakBlock(BlockBreakEvent e) {
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getBlock().getLocation());
        if (island != null) {
            island.blocks.remove(e.getBlock().getLocation());
            island.failedGenerators.remove(e.getBlock().getLocation());
        }
    }
}
