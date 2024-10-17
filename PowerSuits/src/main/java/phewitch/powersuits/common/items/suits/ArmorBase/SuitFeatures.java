package phewitch.powersuits.common.items.suits.ArmorBase;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.ArrayList;

public class SuitFeatures{

    public float maxPower;
    float currentPower;
    public float powerRechargePerSecond;
    public float fallDamageMultiplier;
    public float fallDamageCancellationDistance;
    public double flightVelocity;
    public float flightCost;


    public long lastLaserShot = 0;
    public float laserShotCost = 15f;
    public long lastChestLaserShot = 0;
    public float chestLaserShotCost = 50f;

    public ArrayList<ABILITIES> abilities;
    public ArrayList<MobEffect> fullArmourEffects;

    private final String name;

    public SuitFeatures(SuitTemplate template){
        this.maxPower = template.maxPower;
        this.currentPower = maxPower / 4;
        this.powerRechargePerSecond = template.powerRechargePerSecond;
        this.fallDamageMultiplier = template.fallDamageMultiplier;
        this.fallDamageCancellationDistance = template.fallDamageCancellationDistance;
        this.flightVelocity = template.flightVelocity;
        this.flightCost = template.flightCost;
        this.abilities = template.abilities;
        this.fullArmourEffects = template.fullArmourEffects;
        this.name = template.name;
    }


    public boolean hasPower(float value){ return currentPower >= value; }
    public void addPower(float value){
        currentPower = Math.min(currentPower + value, maxPower);
    }
    public void removePower(float value){
        currentPower = Math.max(currentPower - value, 0);
    }
    public void setPower(float power){
        currentPower = power;
    }

    public boolean canLimitedFlight(){ return abilities.contains(ABILITIES.LIMITED_FLIGHT) && currentPower > 0 && (flightCost == 0 || currentPower - flightCost > 0); }

    public String getModelName(){
        return name.toLowerCase().replaceAll("\\s","").trim();
    }
    public String DisplayName(){
        return name;
    }

    public enum ABILITIES {
        LIMITED_FLIGHT(0),
        FULL_FLIGHT(1),
        SHOOT_ARROWS(2),
        SHOOT_LASERS(3),
        SHOOT_CHEST_LASER(4),
        SENTRY_MODE(5);

        private final int value;

        ABILITIES(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}


