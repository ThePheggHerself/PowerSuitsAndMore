package phewitch.powersuits.Common.Items.Armor.ArmorBase;

import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;

public class SuitFeatures{

    public SuitFeatures(float maxPower, float rechargePerSecond, ArrayList<ABILITIES> abilities, ArrayList<MobEffectInstance> effects){
        this(maxPower, rechargePerSecond, 0, 10, 0.1d, 0, abilities, effects);
    }

    public SuitFeatures(float maxPower, float rechargePerSecond, float fallDamageMultiplier, double flightVelocity, float flightCost, ArrayList<ABILITIES> abilities){
        this(maxPower, rechargePerSecond, fallDamageMultiplier, 10, flightVelocity, flightCost, abilities, new ArrayList<>());
    }

    public SuitFeatures(float maxPower, float rechargePerSecond, float fallDamageMultiplier, float fallDamageCancellationDistance, double flightVelocity, float flightCost, ArrayList<ABILITIES> abilities){
        this(maxPower, rechargePerSecond, fallDamageMultiplier, fallDamageCancellationDistance, flightVelocity, flightCost, abilities, new ArrayList<>());
    }

    public SuitFeatures(float maxPower, float rechargePerSecond, float fallDamageMultiplier, float fallDamageCancellationDistance, double flightVelocity, float flightCost, ArrayList<ABILITIES> abilities, ArrayList<MobEffectInstance> effects){
        this.maxPower = maxPower;
        this.currentPower = maxPower;
        this.powerRechargePerSecond = rechargePerSecond;
        this.fallDamageMultiplier = fallDamageMultiplier;
        this.fallDamageCancellationDistance = fallDamageCancellationDistance;
        this.flightVelocity = flightVelocity;
        this.flightCost = flightCost;
        this.abilities = abilities;
        this.fullArmourEffects = effects;
    }

    public float maxPower;
    public float currentPower;
    public boolean overchargeAllowed = false;
    public float powerRechargePerSecond;
    public float fallDamageMultiplier;
    public float fallDamageCancellationDistance;
    public double flightVelocity;
    public float flightCost;
    public ArrayList<ABILITIES> abilities;
    public ArrayList<MobEffectInstance> fullArmourEffects;

    public void addPower(float add){
        currentPower += add;
        if(currentPower > maxPower && !overchargeAllowed){
            currentPower = maxPower;
        }
    }

    public void removePower(float add){
        currentPower -= add;
        if(currentPower < 0){
            currentPower = 0;
        }
    }

    public void setPower(float power){
        currentPower = power;
    }

    public boolean canLimitedFlight(){
        return abilities.contains(ABILITIES.LIMITED_FLIGHT) && currentPower > 0 && (flightCost == 0 || currentPower - flightCost > 0);
    }

    public enum ABILITIES {
        LIMITED_FLIGHT,
        FULL_FLIGHT,
        SHOOT_ARROWS,
        SHOOT_LASERS,
        SHOOT_CHEST_LASER
    }
}


