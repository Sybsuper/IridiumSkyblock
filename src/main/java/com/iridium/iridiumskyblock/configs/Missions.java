package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.MultiversionMaterials;
import org.bukkit.CropState;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Missions {
    public HashMap<String, HashMap<Integer, Mission>> mission = new HashMap<String, HashMap<Integer, Mission>>() {{
        put("Treasure Hunter", new HashMap<Integer, Mission>() {{
            put(1, new Mission(5, 1000, 100, MissionType.EXPERIENCE, Collections.emptyList()));
            put(2, new Mission(10, 5000, 1000, MissionType.EXPERIENCE, Collections.emptyList()));
            put(3, new Mission(15, 10000, 10000, MissionType.EXPERIENCE, Collections.emptyList()));
        }});
        put("Competitor", new HashMap<Integer, Mission>() {{
            put(1, new Mission(15, 10000, 10000, MissionType.VALUE_INCREASE, Collections.emptyList()));
        }});
        put("Miner", new HashMap<Integer, Mission>() {{
            put(1, new Mission(15, 10000, 1000, MissionType.BLOCK_BREAK, Arrays.asList(MultiversionMaterials.COAL_ORE.toString(), MultiversionMaterials.DIAMOND_ORE.toString(), MultiversionMaterials.EMERALD_ORE.toString(), MultiversionMaterials.GOLD_ORE.toString(), MultiversionMaterials.IRON_ORE.toString(), MultiversionMaterials.LAPIS_ORE.toString(), MultiversionMaterials.NETHER_QUARTZ_ORE.toString(), MultiversionMaterials.REDSTONE_ORE.toString())));
        }});
        put("Farmer", new HashMap<Integer, Mission>() {{
            put(1, new Mission(15, 10000, 5000, MissionType.BLOCK_BREAK, Collections.singletonList(CropState.RIPE.toString())));
        }});
        put("Hunter", new HashMap<Integer, Mission>() {{
            put(1, new Mission(15, 10000, 1000, MissionType.ENTITY_KILL, Collections.emptyList()));
        }});
        put("Fisherman", new HashMap<Integer, Mission>() {{
            put(1, new Mission(15, 10000, 100, MissionType.FISH_CATCH, Collections.emptyList()));
        }});
        put("Builder", new HashMap<Integer, Mission>() {{
            put(1, new Mission(15, 10000, 10000, MissionType.BLOCK_PLACE, Collections.emptyList()));
        }});
    }};

    public static class Mission {
        public int crystalReward;
        public int vaultReward;
        public int amount;
        public MissionType type;
        public List<String> conditions;

        public Mission(int crystalReward, int vaultReward, int amount, MissionType type, List<String> conditions) {
            this.crystalReward = crystalReward;
            this.vaultReward = vaultReward;
            this.amount = amount;
            this.type = type;
            this.conditions = conditions;
        }
    }
}
