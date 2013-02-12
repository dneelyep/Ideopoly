package com.ideopoly.game;

/** Allowable types of cash that Players can use. */
public enum CASH_TYPES {
    // TODO Get rid of total, since it's not a bill type.
    ones("ones", 1), fives("fives", 5), tens("tens", 10), twenties("twenties", 20),
    fifties("fifties", 50), hundreds("hundreds", 100), fiveHundreds("fiveHundreds", 500), total("total", 0);
    // TODO Fix this, again, ugly hack for ("total", 0).

    /** A String description of the given bill type. */                                  private final String description;
    
    /** The number of bills of a given CASH_TYPE given to each Player at the beginning of a game. */
    private final int initialValue;
    
    /** An integer representation of the cash type. */
    private final int intValue;

    CASH_TYPES(String desc, int newInt, int initialValue) {
        description = desc;
        intValue = newInt;
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

    public static CASH_TYPES getTypeFromDescription(String description) {
        switch (description) {
            case "ones":  return ones;
            case "fives": return fives;
            case "tens":  return tens;
            case "twenties": return twenties;
            case "fifties":  return fifties;
            case "hundreds": return hundreds;
            case "fiveHundreds": return fiveHundreds;
            case "total": return total;
            default: return fiveHundreds;
        }
    }
}