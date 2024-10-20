package phewitch.powersuits.common.item.suits.armorbase.enums;

public enum ActiveAbilities {
    SHOOT_ARROWS(1),
    SHOOT_LASERS(2),
    SHOOT_CHEST_LASER(3),
    SENTRY_MODE(4),
    SHOOT_FIRE_ARROWS(5),
    SHOOT_FLAMETHROWER(6),
    SHOOT_ENDER_SHOT(7),
    TELEPORT(8),
    SHOOT_WITHER_SKULLS(9),
    SONIC_BOOM(10),
    WATER_DASH(11);
    private final int value;

    ActiveAbilities(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
