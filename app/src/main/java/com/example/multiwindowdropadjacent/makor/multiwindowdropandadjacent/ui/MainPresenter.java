package com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.ui;

import com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.model.PokemonConstants;
import com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.utils.Pokeball;
import com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.utils.PokemonUrlCreator;

public class MainPresenter {

    private MainView mMainView = new MainView.Empty();

    private int currentPokemonId = 1;

    public void setView(final MainView mainView) {
        mMainView = mainView;
        loadPokemonImage();
    }

    private void loadPokemonImage() {
        mMainView.showProgressBar();
        mMainView.loadPokemonImage(PokemonUrlCreator.createImageUrl(currentPokemonId));
    }

    public void clearView() {
        mMainView = new MainView.Empty();
    }

    public void onNextClick() {
        currentPokemonId++;
        if (currentPokemonId > PokemonConstants.POKEMONS_LIMIT) currentPokemonId = 1;
        loadPokemonImage();
    }

    public void onPreviousClick() {
        currentPokemonId--;
        if (currentPokemonId == 0) currentPokemonId = PokemonConstants.POKEMONS_LIMIT;
        loadPokemonImage();
    }

    public void pokeballDropped(final String droppedText) {
        if (Pokeball.isPokeballDropped(droppedText)) {
            mMainView.sendPokemonImageUrl(PokemonUrlCreator.createImageUrl(currentPokemonId), currentPokemonId);
        }
    }

    public void onPokemonIdFromAdjacent(final String pokemonId) {
        currentPokemonId = Integer.parseInt(pokemonId);
    }

    public void onPokemonImageLoaded() {
        mMainView.showPokemonImage();
    }
}
