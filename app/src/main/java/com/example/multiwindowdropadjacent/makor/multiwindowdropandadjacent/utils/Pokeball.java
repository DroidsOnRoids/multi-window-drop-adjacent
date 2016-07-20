package com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.utils;

public class Pokeball {

    private static final String POKEBALL = "POKEBALL";

    public static boolean isPokeballDropped(final String droppedText) {
        return POKEBALL.equals(droppedText);
    }
}
