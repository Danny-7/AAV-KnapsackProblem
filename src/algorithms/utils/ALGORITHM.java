package algorithms.utils;

public enum ALGORITHM {
    GREEDY("GREEDY"), DYNAMIC("DYNAMIC"), PSE("PSE");

    private final String algorithm;

    ALGORITHM(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Return true if the string is equal to an enum
     * @param test string to test
     * @return true or false
     */
    public static boolean contains(String test) {
        for (ALGORITHM a : ALGORITHM.values()) {
            if (a.algorithm.equals(test.toUpperCase()))
                return true;
        }
        return false;
    }
}

