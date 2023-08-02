package edu.csis.model;


/**
 * A generic record class representing a simple pair of two values of different types.
 * mainly used to display some things learned in this class
 *
 * @param <F> The type of the first value in the pair.
 * @param <S> The type of the second value in the pair.
 * @author bakumah
 */
public record Pair<F, S>(F first, S second) {}
