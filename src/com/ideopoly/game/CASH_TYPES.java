package com.ideopoly.game;

/** Allowable types of cash that Players can use. */
public enum CASH_TYPES {
    // TODO Get rid of total, since it's not a bill type.
    ones("ones", 1, 5), fives("fives", 5, 5), tens("tens", 10, 5), twenties("twenties", 20, 6),
    fifties("fifties", 50, 2), hundreds("hundreds", 100, 2), fiveHundreds("fiveHundreds", 500, 2), total("total", 0, 0);
    // TODO Fix this, again, ugly hack for ("total", 0).

    /** A String description of the given bill type. */
    private final String description;

    /** An integer representation of the cash type. */
    private final int intValue;

    /** The number of bills of a given CASH_TYPE given to each Player at the beginning of a game. */
    private final int initialValue;

    CASH_TYPES(String description, int intValue, int initialValue) {
        this.description = description;
        this.intValue = intValue;
        this.initialValue = initialValue;
    }

    /** Get the description of this bill type. */
    public String getDescription() {
        return description;
    }

    /** Get this CASH_TYPES value as an integer. */
    public int asInt() {
        return intValue;
    }

    /** Get the number of bills of a given CASH_TYPE allocated to each Player at the
     * beginning of a new game. */
    public int getInitialValue() {
        return initialValue;
    }
}
