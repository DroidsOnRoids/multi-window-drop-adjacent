package com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.ui;

public interface MainView {

    void loadPokemonImage(String pokemonImageUrl);

    void sendPokemonImageUrl(String imageLinkUrl, final int pokemonId);

    void showPokemonImage();

    void showProgressBar();

    class Empty implements MainView {

        @Override
        public void loadPokemonImage(final String pokemonImageUrl) {

        }

        @Override
        public void sendPokemonImageUrl(final String imageLinkUrl, final int pokemonId) {

        }

        @Override
        public void showPokemonImage() {

        }

        @Override
        public void showProgressBar() {

        }
    }
}
