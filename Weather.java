/**
 * A class that represents the weather card.
 * A weather card has a value from 1 to 60 and a life preserver value.
 * No life preserver if the weather value is less than 13 or greater than 48.
 * There is a full life preserver if the weather value is between 25 and 36 (inclusive).
 * There is a half life preserver if the weather value is not the above cases.
 * 
 */
public class Weather {
    /**
     * No life preserver is denoted by the constant 0.
     */
    private static final int NO_LIFE_PRESERVER = 0;
    /**
     * Half life preserver is denoted by the constant 1.
     */
     */
    private static final int HALF_LIFE_PRESERVER = 1;
    /**
     * Full life preserver is denoted by the constant 2.
     */
    private static final int FULL_LIFE_PRESERVER = 2;
    private final int lifePreserver;
    private final int value;
    /**
     * Constructor
     */
    public Weather(int value) {
        //TODO
    }
    /**
     * Returns the value of the card.
     */
    public int getValue() {
        //TODO
    }
    /**
     * Returns the life preserver value of the card.
     * It should return either 0, 1, or 2 for that weather card.
     * 
     * Don't be confuse with the life preserver value of the player.
     */
    public int getLifePreserver() {
        //TODO
    }

    // not necessarily to implement this method, but you can if you want to
    public String toString() {
        //TODO
    }
}