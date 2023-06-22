package mobile.pokepedia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PokemonRecyclerViewAdapter extends RecyclerView.Adapter<PokemonRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<PokemonModel> pokemonModels;
    private final RecyclerViewInterface itemClickListener;

    public PokemonRecyclerViewAdapter(ArrayList<PokemonModel> pokemonModels, RecyclerViewInterface itemClickListener) {
        this.pokemonModels = pokemonModels;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PokemonModel pokemon = pokemonModels.get(position);

        holder.pokemonName.setText(pokemon.getName());
        holder.pokemonNumber.setText(pokemon.getNumber());

        // Set the click listener for the item view
        holder.itemView.setOnClickListener(view -> itemClickListener.onItemClick(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return pokemonModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView pokemonName;
        public final TextView pokemonNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.idPokemonName);
            pokemonNumber = itemView.findViewById(R.id.idPokemonCode);
        }
    }
}
