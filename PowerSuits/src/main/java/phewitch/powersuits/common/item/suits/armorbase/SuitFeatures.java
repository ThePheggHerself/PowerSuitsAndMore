package phewitch.powersuits.common.item.suits.armorbase;

import com.google.j2objc.annotations.Weak;
import net.minecraft.world.effect.MobEffect;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SuitFeatures{

    public float maxPower;
    public float currentPower;
    public float pRechargePS;
    public float fallDmgMult;
    public float fallDmgCancDist;
    public double flightVelocity;
    public float flightCost;


    public int projCooldown = 0;

    public ArrayList<SuitAbility> activeA;
    public ArrayList<PassiveAbilities> passiveA;
    public ChargeType chargeType;
    public ArrayList<Weakness> weaknesses;

    public ArrayList<MobEffect> effects;

    private final String name;

    public SuitFeatures(float MaxPower, float RechargePerSecond, float FallDamageMultiplier, float FallDamageCancellationDistance, double FlightVelocity,
                        float FlightCostPerSecond, ArrayList<SuitAbility> ActiveAbilities, ArrayList<PassiveAbilities> PassiveAbilities, ChargeType ChargeType,
                        ArrayList<Weakness> Weaknesses, ArrayList<MobEffect> Effects, String Name){
        this.maxPower = MaxPower;
        this.currentPower = maxPower / 4;
        this.pRechargePS = RechargePerSecond;
        this.fallDmgMult = FallDamageMultiplier;
        this.fallDmgCancDist = FallDamageCancellationDistance;
        this.flightVelocity = FlightVelocity;
        this.flightCost = FlightCostPerSecond;
        this.activeA = ActiveAbilities;
        this.passiveA = PassiveAbilities;
        this.chargeType = ChargeType;
        this.weaknesses = Weaknesses;
        this.effects = Effects;
        this.name = Name;
    }


    public boolean hasPower(float value){
        if(value < 0)
            return currentPower == maxPower;
        return currentPower >= value;
    }
    public void addPower(float value){
        if(value < 0)
            currentPower = maxPower;
        else
            currentPower = Math.min(currentPower + value, maxPower);
    }
    public void removePower(float value){
        if(value < 0)
            currentPower = 0;
        else
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


