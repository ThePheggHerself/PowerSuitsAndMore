package phewitch.powersuits.common.items.suits.ArmorBase;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.ArrayList;

public class SuitFeatures{

    public SuitFeatures(float maxPower, float rechargePerSecond, ArrayList<ABILITIES> abilities, ArrayList<MobEffect> effects){
        this(maxPower, rechargePerSecond, 0, 10, 0.1d, 0, abilities, effects);
    }

    public SuitFeatures(float maxPower, float rechargePerSecond, float fallDamageMultiplier, double flightVelocity, float flightCost, ArrayList<ABILITIES> abilities){
        this(maxPower, rechargePerSecond, fallDamageMultiplier, 10, flightVelocity, flightCost, abilities, new ArrayList<>());
    }

    public SuitFeatures(float maxPower, float rechargePerSecond, float fallDamageMultiplier, float fallDamageCancellationDistance, double flightVelocity, float flightCost, ArrayList<ABILITIES> abilities){
        this(maxPower, rechargePerSecond, fallDamageMultiplier, fallDamageCancellationDistance, flightVelocity, flightCost, abilities, new ArrayList<>());
    }

    public SuitFeatures(float maxPower, float rechargePerSecond, float fallDamageMultiplier, float fallDamageCancellationDistance, double flightVelocity, float flightCost,
                        ArrayList<ABILITIES> abilities, ArrayList<MobEffect> effects){
        this.maxPower = maxPower;
        this.currentPower = maxPower / 4;
        this.powerRechargePerSecond = rechargePerSecond;
        this.fallDamageMultiplier = fallDamageMultiplier;
        this.fallDamageCancellationDistance = fallDamageCancellationDistance;
        this.flightVelocity = flightVelocity;
        this.flightCost = flightCost;
        this.abilities = abilities;
        this.fullArmourEffects = effects;
    }

    public float maxPower;
    float currentPower;
    public float powerRechargePerSecond;
    public float fallDamageMultiplier;
    public float fallDamageCancellationDistance;
    public double flightVelocity;
    public float flightCost;
    public ArrayList<ABILITIES> abilities;
    public ArrayList<MobEffect> fullArmourEffects;

    public long lastLaserShot = 0;
    public float laserShotCost = 15f;
    public long lastChestLaserShot = 0;
    public float chestLaserShotCost = 50f;

    public void addPower(float value){
        currentPower = Math.min(currentPower + value, maxPower);
    }

    public void removePower(float value){
        currentPower = Math.max(currentPower - value, 0);
    }

    public boolean hasPower(float value){
        return currentPower >= value;
    }

    public void setPower(float power){
        currentPower = power;
    }

    public boolean canLimitedFlight(){
        return abilities.contains(ABILITIES.LIMITED_FLIGHT) && currentPower > 0 && (flightCost == 0 || currentPower - flightCost > 0);
    }

    public enum ABILITIES {
        LIMITED_FLIGHT(0),
        FULL_FLIGHT(1),
        SHOOT_ARROWS(2),
        SHOOT_LASERS(3),
        SHOOT_CHEST_LASER(4),
        SENTRY_MODE(5);

        private final int value;
        private ABILITIES(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}


