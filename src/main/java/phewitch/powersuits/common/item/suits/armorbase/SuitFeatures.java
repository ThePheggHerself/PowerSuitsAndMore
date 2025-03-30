package phewitch.powersuits.common.item.suits.armorbase;

import net.minecraft.world.effect.MobEffect;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;

import java.util.ArrayList;

public class SuitFeatures{

    public int maxPower;
    public int recharge;
    public float fallDmgMult;
    public float fallDmgCancDist;
    public double flightVelocity;
    public int flightCost;
    public ArrayList<SuitAbility> activeA;
    public ArrayList<PassiveAbilities> passiveA;
    public ChargeType chargeType;
    public ArrayList<Weakness> weaknesses;

    public ArrayList<MobEffect> effects;

    private final String name;

    public SuitFeatures(int MaxPower, int RechargePerTick, float FallDamageMultiplier, float FallDamageCancellationDistance, double FlightVelocity,
                        int FlightCostPerTick, ArrayList<SuitAbility> ActiveAbilities, ArrayList<PassiveAbilities> PassiveAbilities, ChargeType ChargeType,
                        ArrayList<Weakness> Weaknesses, ArrayList<MobEffect> Effects, String Name){
        this.maxPower = MaxPower;
        this.recharge = RechargePerTick;
        this.fallDmgMult = FallDamageMultiplier;
        this.fallDmgCancDist = FallDamageCancellationDistance;
        this.flightVelocity = FlightVelocity;
        this.flightCost = FlightCostPerTick;
        this.activeA = ActiveAbilities;
        this.passiveA = PassiveAbilities;
        this.chargeType = ChargeType;
        this.weaknesses = Weaknesses;
        this.effects = Effects;
        this.name = Name;
    }
    
    public String getModelName(){
        return name.toLowerCase().replaceAll("\\s","").trim();
    }
    public String DisplayName(){
        return name;
    }
}


