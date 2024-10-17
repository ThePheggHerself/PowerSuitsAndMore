package phewitch.powersuits.common.items.suits.ArmorBase;

import net.minecraft.world.effect.MobEffect;

import java.util.ArrayList;

public class SuitTemplate {
    public float maxPower;
    public float powerRechargePerSecond;
    public float fallDamageMultiplier;
    public float fallDamageCancellationDistance;
    public double flightVelocity;
    public float flightCost;
    public ArrayList<SuitFeatures.ABILITIES> abilities;
    public ArrayList<MobEffect> fullArmourEffects;
    public String name;

    public SuitTemplate(String name, float maxPower, float rechargePerSecond, ArrayList<SuitFeatures.ABILITIES> abilities, ArrayList<MobEffect> effects){
        this(name, maxPower, rechargePerSecond, 0, 10, 0.1d, 0, abilities, effects);
    }
    public SuitTemplate(String name, float maxPower, float rechargePerSecond, float fallDamageMultiplier, double flightVelocity, float flightCost, ArrayList<SuitFeatures.ABILITIES> abilities){
        this(name, maxPower, rechargePerSecond, fallDamageMultiplier, 10, flightVelocity, flightCost, abilities, new ArrayList<>());
    }
    public SuitTemplate(String name, float maxPower, float rechargePerSecond, float fallDamageMultiplier, float fallDamageCancellationDistance, double flightVelocity, float flightCost,
                        ArrayList<SuitFeatures.ABILITIES> abilities){
        this(name, maxPower, rechargePerSecond, fallDamageMultiplier, fallDamageCancellationDistance, flightVelocity, flightCost, abilities, new ArrayList<>());
    }

    public SuitTemplate(String name, float maxPower, float rechargePerSecond, float fallDamageMultiplier, float fallDamageCancellationDistance, double flightVelocity, float flightCost,
                        ArrayList<SuitFeatures.ABILITIES> abilities, ArrayList<MobEffect> effects){
        this.maxPower = maxPower;
        this.powerRechargePerSecond = rechargePerSecond;
        this.fallDamageMultiplier = fallDamageMultiplier;
        this.fallDamageCancellationDistance = fallDamageCancellationDistance;
        this.flightVelocity = flightVelocity;
        this.flightCost = flightCost;
        this.abilities = abilities;
        this.fullArmourEffects = effects;
        this.name = name;
    }
}
