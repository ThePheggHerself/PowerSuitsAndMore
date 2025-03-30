package phewitch.powersuits.common.capabilities.data;

import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.List;

public class PlayerOSSCapabilityData {
        private List<String> suits = new ArrayList<>();

        public List<String> getSuits() {
            return suits;
        }

        public void addSuit(String suit) {
            if (!suits.contains(suit)) {
                suits.add(suit);

            }
        }

        public void removeSuit(String suit) {
            if (suits.contains(suit)) {
                suits.remove(suit);
            }
        }

        public void copyFrom(PlayerOSSCapabilityData source) {
            this.suits = source.suits;
        }

        public void saveNBTData(CompoundTag nbt) {
            nbt.putString("suits", new Gson().toJson(suits));
        }

        public void loadNBTData(CompoundTag nbt) {
            suits = new Gson().fromJson(nbt.getString("suits"), List.class);
        }
    }
