package com.ideopoly.game;

/** Allowable types of cash that Players can use. */
public enum CASH_TYPES {
    // TODO Get rid of total, since it's not a bill type.
    ones(1, 5), fives(5, 5), tens(10, 5), twenties(20, 6),
    fifties(50, 2), hundreds(100, 2), fiveHundreds(500, 2), total(0, 0);
    // TODO Fix this, again, ugly hack for ("total", 0).

    /** An integer representation of the cash type. */
    private final int intValue;

    /** The number of bills of a given CASH_TYPE given to each Player at the beginning of a game. */
    private final int initialValue;

    CASH_TYPES(int intValue, int initialValue) {
        this.intValue = intValue;
        this.initialValue = initialValue;
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
