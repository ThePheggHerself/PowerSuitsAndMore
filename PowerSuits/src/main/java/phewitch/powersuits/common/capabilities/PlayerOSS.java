package phewitch.powersuits.common.capabilities;

import com.google.gson.Gson;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;

public class PlayerOSS {
    private List<String> suits = new ArrayList<>();

    public List<String> getSuits(){
        return suits;
    }

    public void addSuit(String suit){
        if(!suits.contains(suit))
            suits.add(suit);
    }
    public void removeSuit(String suit){
        if(suit.contains(suit))
            suits.remove(suit);
    }

    public void copyFrom(PlayerOSS source){
        this.suits = source.suits;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("suits", new Gson().toJson(suits));
    }

    public void loadNBTData(CompoundTag nbt) {
        suits = new Gson().fromJson(nbt.getString("suits"), List.class);
    }
}
