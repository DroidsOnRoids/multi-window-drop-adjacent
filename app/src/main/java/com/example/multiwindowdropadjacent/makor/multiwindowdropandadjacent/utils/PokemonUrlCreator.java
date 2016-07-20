package com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.utils;

public class PokemonUrlCreator {

    public static String createImageUrl(final int pokemonId) {
        return "http://pokeapi.co/media/sprites/pokemon/" + pokemonId + ".png";
    }
}
