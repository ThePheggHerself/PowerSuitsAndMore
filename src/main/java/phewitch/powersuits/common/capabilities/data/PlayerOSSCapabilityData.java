package phewitch.powersuits.common.capabilities.data;

import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.List;

public class PlayerOSSCapabilityData {
        private List<String> suits = new ArrayList<>();
        private Boolean autoDeployMark7 = true;

        public List<String> getSuits() {
            return suits;
        }
        public void addSuit(String suit) {
            if (!suits.contains(suit)) {
                suits.add(suit);
            }
        }
        public void removeSuit(String suit) {
            suits.remove(suit);
        }

        public Boolean getAutoDeployMark7(){
            return autoDeployMark7;
        }
        public void setAutoDeployMark7(Boolean value){
            autoDeployMark7 = value;
        }

        public void copyFrom(PlayerOSSCapabilityData source) {
            this.suits = source.suits;
            this.autoDeployMark7 = source.autoDeployMark7;
        }

        public void saveNBTData(CompoundTag nbt) {
            nbt.putString("suits", new Gson().toJson(suits));
            nbt.putBoolean("autoDeployMk7", autoDeployMark7);
        }
        public void loadNBTData(CompoundTag nbt) {
            this.suits = new Gson().fromJson(nbt.getString("suits"), List.class);
            this.autoDeployMark7 = nbt.getBoolean("autoDeployMk7");
        }
    }
