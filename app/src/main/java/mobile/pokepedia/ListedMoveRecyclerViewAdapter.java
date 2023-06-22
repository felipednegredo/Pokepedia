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

public class ListedMoveRecyclerViewAdapter extends RecyclerView.Adapter<ListedMoveRecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ListedMoveModel> listedMoveModels;

    public ListedMoveRecyclerViewAdapter(Context context, ArrayList<ListedMoveModel> listedMoveModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.listedMoveModels = listedMoveModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.moveview, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewName.setText(listedMoveModels.get(position).getMoveName());
        holder.textViewMove.setText(listedMoveModels.get(position).getMoveType());
        holder.imageView.setImageResource(listedMoveModels.get(position).getMoveRank());
    }

    @Override
    public int getItemCount() {
        return listedMoveModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewMove, textViewName;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idRankMove);
            textViewMove = itemView.findViewById(R.id.idNameMove);
            textViewName = itemView.findViewById(R.id.idTypeMove);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
