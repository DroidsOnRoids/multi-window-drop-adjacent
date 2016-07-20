package com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.R;
import com.example.multiwindowdropadjacent.makor.multiwindowdropandadjacent.model.PokemonConstants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.image_pokemon) ImageView mImagePokemon;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter();
        mImagePokemon.setOnDragListener((view, dragEvent) -> sendPokemonImageUrlIfDrop(dragEvent));

        checkIfAdjacentAndShowDetails();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.clearView();
    }

    private boolean sendPokemonImageUrlIfDrop(final DragEvent dragEvent) {
        if (isDropAction(dragEvent)) {
            mPresenter.pokeballDropped((dragEvent.getClipData().getItemAt(0)).getText().toString());
        }
        return true;
    }

    private boolean isDropAction(final DragEvent dragEvent) {
        return dragEvent.getAction() == DragEvent.ACTION_DROP;
    }

    private void checkIfAdjacentAndShowDetails() {
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mPresenter.onPokemonIdFromAdjacent(extras.getString(PokemonConstants.POKEMON_ID));
        }
    }

    @OnClick(R.id.button_next)
    public void onNextClick() {
        mPresenter.onNextClick();
    }

    @OnClick(R.id.button_previous)
    public void onPreviousClick() {
        mPresenter.onPreviousClick();
    }

    @Override
    public void loadPokemonImage(final String pokemonImageUrl) {
        Picasso.with(this).load(pokemonImageUrl).fit().centerCrop().into(mImagePokemon, new Callback() {

            @Override
            public void onSuccess() {
                mPresenter.onPokemonImageLoaded();
            }

            @Override
            public void onError() {
                Log.d("loadPokemonImage", "onError()");
            }
        });
    }

    @Override
    public void sendPokemonImageUrl(final String imageLinkUrl, final int pokemonId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imageLinkUrl));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(PokemonConstants.POKEMON_ID, pokemonId);
        intent.putExtra(PokemonConstants.POKEBALL_IMAGE_URL, imageLinkUrl);
        startActivity(intent);
    }

    @Override
    public void showPokemonImage() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mImagePokemon.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mImagePokemon.setVisibility(View.INVISIBLE);
    }
}
