package phewitch.powersuits.common.item.suits.armorbase.enums;

public enum PassiveAbilities {
    LIMITED_FLIGHT(0),
    FULL_FLIGHT(1),
    WITHER_PUNCH(2),
    WITHER_RESISTANCE(3),
    WATER_CONDUIT(4),
    WATER_SPEED(5),
    SILENT_SUIT(6),
    BLOCK_ENDERMAN_LOOK(7),
    WALK_POWDERED_SNOW(8);

    private final int value;

    PassiveAbilities(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
