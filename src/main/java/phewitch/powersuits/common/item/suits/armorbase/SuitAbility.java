package phewitch.powersuits.common.item.suits.armorbase;

import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;

public class SuitAbility {

    public static SuitAbility SHOOT_ARROWS_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_ARROWS, 0);
    public static SuitAbility SHOOT_LASERS_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_LASERS, 200);
    public static SuitAbility SHOOT_CHEST_LASER_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_CHEST_LASER, 1000);
    public static SuitAbility SENTRY_MODE_ABILITY = new SuitAbility(ActiveAbilities.SENTRY_MODE, 250);
    public static SuitAbility SHOOT_FIRE_ARROWS_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_FIRE_ARROWS, 400);
    public static SuitAbility SHOOT_FLAMETHROWER_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_FLAMETHROWER, 600);
    public static SuitAbility SHOOT_ENDER_SHOT_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_ENDER_SHOT, 650);
    public static SuitAbility TELEPORT_ABILITY = new SuitAbility(ActiveAbilities.TELEPORT, 1000);
    public static SuitAbility SHOOT_WITHER_SKULLS_ABILITY = new SuitAbility(ActiveAbilities.SHOOT_WITHER_SKULLS, 950);
    public static SuitAbility SONIC_BOOM_ABILITY = new SuitAbility(ActiveAbilities.SONIC_BOOM, 1000);
    public static SuitAbility WATER_DASH_ABILITY = new SuitAbility(ActiveAbilities.WATER_DASH, 10);

    public ActiveAbilities AbilityType;
    public int Cost;
    public float Cooldown;

    public SuitAbility(ActiveAbilities Type, int Cost){
        AbilityType = Type;
        this.Cost = Cost;
    }
    public SuitAbility(ActiveAbilities Type, int Cost, float Cooldown){
        AbilityType = Type;
        this.Cost = Cost;
        this.Cooldown = Cooldown;
    }
}
