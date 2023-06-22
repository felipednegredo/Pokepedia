package mobile.pokepedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class  PokemonInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info);

        configureBackButton();

        PokemonInfoDetailFragment pokemonInfoDetailFragment = new PokemonInfoDetailFragment();
        PokemonInfoStatsFragment pokemonInfoStatsFragment = new PokemonInfoStatsFragment();
        PokemonInfoMovesFragment pokemonInfoMovesFragment = new PokemonInfoMovesFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.flFragment, pokemonInfoDetailFragment);
        transaction.commit();

        configureDetailBtn(pokemonInfoDetailFragment);
        configureStatsBtn(pokemonInfoStatsFragment);
        configureMovesBtn(pokemonInfoMovesFragment);
    }

    private void configureBackButton() {
        ImageView backButton = (ImageView) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void configureDetailBtn(PokemonInfoDetailFragment pokemonInfoDetailFragment) {
        Button detailBtn = (Button) findViewById(R.id.buttonInfoDetail);
        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.flFragment, pokemonInfoDetailFragment);
                transaction.commit();
            }
        });
    }

    private void configureStatsBtn(PokemonInfoStatsFragment pokemonInfoStatsFragment) {
        Button statsBtn = (Button) findViewById(R.id.buttonInfoStats);
        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.flFragment, pokemonInfoStatsFragment);
                transaction.commit();
            }
        });
    }

    private void configureMovesBtn(PokemonInfoMovesFragment pokemonInfoMovesFragment) {
        //put new Intent like a searchResult

        Button movesBtn = (Button) findViewById(R.id.buttonInfoMoves);

        movesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.flFragment, pokemonInfoMovesFragment);
                transaction.commit();
            }
        });
    }
}