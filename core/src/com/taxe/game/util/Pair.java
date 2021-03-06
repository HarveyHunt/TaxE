package com.taxe.game.util;

/**
 * As Java doesn't have tuples, we need a way to mimic a 2 element tuple
 * to get around the limitations of executeCommand.
 * <p/>
 * XXX: This class will fuck up if you use it in a HashMap, as equals and
 * hashCode haven't been written....
 */
public class Pair<Left, Right> {
    public final Left left;
    public final Right right;

    public Pair(Left left, Right right) {
        this.left = left;
        this.right = right;
    }
}
