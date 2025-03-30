package phewitch.powersuits.common.item.suits.armorbase.enums;

public enum ChargeType {
    ON_GROUND(0),
    IN_FIRE(1),
    IN_FIRE_OR_LAVA(2),
    IN_WATER(3),
    MANUAL(4),
    LIFE_DRAIN(5);

    private final int value;

    ChargeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
