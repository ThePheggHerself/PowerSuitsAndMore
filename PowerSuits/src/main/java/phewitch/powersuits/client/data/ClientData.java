package phewitch.powersuits.client.data;

public class ClientData {
    private static int suitPower;
    private static int maxSuitPower;

    public static void setPower(int power){
        ClientData.suitPower = power;
    }

    public static int getSuitPower(){
        return suitPower;
    }
    public static void setMaxPower(int power){
        ClientData.maxSuitPower = power;
    }

    public static int getMaxSuitPower(){
        return maxSuitPower;
    }
}
