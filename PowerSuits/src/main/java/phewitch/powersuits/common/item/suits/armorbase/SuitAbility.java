package phewitch.powersuits.common.item.suits.armorbase;

import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;

public class SuitAbility {

    public static SuitAbility SHOOT_ARROWS_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_ARROWS, 10);
    public static SuitAbility SHOOT_LASERS_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_LASERS, 10);
    public static SuitAbility SHOOT_CHEST_LASER_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_CHEST_LASER, 10);
    public static SuitAbility SENTRY_MODE_ABILITY = new SuitAbility(ActiveAbilities.SENTRY_MODE, 10);
    public static SuitAbility SHOOT_FIRE_ARROWS_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_FIRE_ARROWS, 10);
    public static SuitAbility SHOOT_FLAMETHROWER_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_FLAMETHROWER, 10);
    public static SuitAbility SHOOT_ENDER_SHOT_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_ENDER_SHOT, 10);
    public static SuitAbility TELEPORT_ABILITY = new SuitAbility(ActiveAbilities.TELEPORT, 10);
    public static SuitAbility SHOOT_WITHER_SKULLS_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_WITHER_SKULLS, 10);
    public static SuitAbility SONIC_BOOM_ABILITY = new SuitAbility(ActiveAbilities.SONIC_BOOM, 10);
    public static SuitAbility WATER_DASH_ABILITY = new SuitAbility(ActiveAbilities.WATER_DASH, 10);

    public ActiveAbilities AbilityType;
    public float Cost;
    public float Cooldown;

    public SuitAbility(ActiveAbilities Type, float Cost){
        AbilityType = Type;
        this.Cost = Cost;
    }
    public SuitAbility(ActiveAbilities Type, float Cost, float Cooldown){
        AbilityType = Type;
        this.Cost = Cost;
        this.Cooldown = Cooldown;
    }
}
