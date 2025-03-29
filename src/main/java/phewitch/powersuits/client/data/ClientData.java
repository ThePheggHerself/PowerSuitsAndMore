package phewitch.powersuits.client.data;

import java.util.ArrayList;
import java.util.List;

public class ClientData {
    private static List<String> OSSSuits = new ArrayList<>();

    public static void setOSSSuits(List<String> Suits){
        OSSSuits = Suits;
    }
    public static List<String> getOSSSuits(){
        return OSSSuits;
    }
    public static void RemoveSuit(String SuitName){
        OSSSuits.remove(SuitName);
    }
}
