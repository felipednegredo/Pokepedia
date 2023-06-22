package mobile.pokepedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureSearchView();
    }

    private void configureSearchView() {
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, PokemonRecyclerViewAdapter.class);
                intent.putExtra("QUERY", query);
                startActivity(intent);
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }
}
