package mobile.pokepedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListedPokemonRecyclerViewAdapter extends RecyclerView.Adapter<ListedPokemonRecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ListedPokemonModel> listedPokemonModels;

    public ListedPokemonRecyclerViewAdapter(Context context, ArrayList<ListedPokemonModel> listedPokemonModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.listedPokemonModels = listedPokemonModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public ListedPokemonRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Onde infla o layout (dando uma aparência às colunas)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_result_row, parent, false);

        return new ListedPokemonRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ListedPokemonRecyclerViewAdapter.MyViewHolder holder, int position) {
        // Onde concede valores às views criadas no arquivo layout recycler_view_file baseado na posição do recycler view
        holder.textViewName.setText(listedPokemonModels.get(position).getPokemonName());
        holder.textViewNumber.setText(listedPokemonModels.get(position).getPokemonNumber());
        holder.imageView.setImageResource(listedPokemonModels.get(position).getPokemonRank());

    }

    @Override
    public int getItemCount() {
        // O recycler view quer saber quantos itens você tem no total
        return listedPokemonModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // Pega todas as views do arquivo layout recycler_view_row como o método onCreate

        ImageView imageView;
        TextView textViewNumber, textViewName;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.idRank);
            textViewName = itemView.findViewById(R.id.idPokemonName);
            textViewNumber = itemView.findViewById(R.id.idPokemonCode);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
