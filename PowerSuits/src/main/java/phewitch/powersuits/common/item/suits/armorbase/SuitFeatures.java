package phewitch.powersuits.common.item.suits.armorbase;

import net.minecraft.world.effect.MobEffect;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;

import java.util.ArrayList;

public class SuitFeatures{

    public float maxPower;
    float currentPower;
    public float pRechargePS;
    public float fallDmgMult;
    public float fallDmgCancDist;
    public double flightVelocity;
    public float flightCost;


    public long lastLaserShot = 0;
    public float laserShotCost = 15f;
    public long lastChestLaserShot = 0;
    public float chestLaserShotCost = 50f;

    public ArrayList<ActiveAbilities> activeA;
    public ArrayList<PassiveAbilities> passiveA;
    public ChargeType chargeType;
    public ArrayList<Weakness> weaknesses;

    public ArrayList<MobEffect> effects;

    private final String name;

    public SuitFeatures(SuitTemplate template){
        this.maxPower = template.maxPower;
        this.currentPower = maxPower / 4;
        this.pRechargePS = template.powerRechargePerSecond;
        this.fallDmgMult = template.fallDamageMultiplier;
        this.fallDmgCancDist = template.fallDamageCancellationDistance;
        this.flightVelocity = template.flightVelocity;
        this.flightCost = template.flightCost;
        this.activeA = template.activeAbilities;
        this.passiveA = template.passiveAbilities;
        this.chargeType = template.chargeType;
        this.weaknesses = template.weaknesses;
        this.effects = template.fullArmourEffects;
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

    public boolean canLimitedFlight(){ return passiveA.contains(PassiveAbilities.LIMITED_FLIGHT) && currentPower > 0 && (flightCost == 0 || currentPower - flightCost > 0); }

    public String getModelName(){
        return name.toLowerCase().replaceAll("\\s","").trim();
    }
    public String DisplayName(){
        return name;
    }
}


