package phewitch.powersuits.common.item.suits.armorbase.enums;

public enum Weakness {
    FIRE(0),
    WATER(1);

    private final int value;

    Weakness(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
