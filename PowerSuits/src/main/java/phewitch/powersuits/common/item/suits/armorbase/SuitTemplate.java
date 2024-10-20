package phewitch.powersuits.common.item.suits.armorbase;

import net.minecraft.world.effect.MobEffect;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;

import java.util.ArrayList;

public class SuitTemplate {
    public float maxPower;
    public float powerRechargePerSecond;
    public float fallDamageMultiplier;
    public float fallDamageCancellationDistance;
    public double flightVelocity;
    public float flightCost;

    public ArrayList<ActiveAbilities> activeAbilities;
    public ArrayList<PassiveAbilities> passiveAbilities;
    public ChargeType chargeType;
    public ArrayList<Weakness> weaknesses;
    public ArrayList<MobEffect> fullArmourEffects;
    public String name;

    public SuitTemplate(String name, float maxPwr, float rechargePS, float fallDmgMulti, float fallDmgCalDist, double flyVel, float flyCst,
                        ArrayList<ActiveAbilities> aA, ArrayList<PassiveAbilities> pA, ChargeType cT, ArrayList<Weakness> weaknesses, ArrayList<MobEffect> effects){
        this.maxPower = maxPwr;
        this.powerRechargePerSecond = rechargePS;
        this.fallDamageMultiplier = fallDmgMulti;
        this.fallDamageCancellationDistance = fallDmgCalDist;
        this.flightVelocity = flyVel;
        this.flightCost = flyCst;
        this.activeAbilities = aA;
        this.passiveAbilities = pA;
        this.chargeType = cT;
        this.weaknesses = weaknesses;
        this.fullArmourEffects = effects;
        this.name = name;
    }
}
