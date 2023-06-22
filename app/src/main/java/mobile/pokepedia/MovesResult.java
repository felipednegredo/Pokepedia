package mobile.pokepedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovesResult extends Fragment implements RecyclerViewInterface {

    ArrayList<ListedMoveModel> listedMoveModels = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pokemon_info_moves, container, false);

        configureBackButton(rootView);

        RecyclerView recyclerView = rootView.findViewById(R.id.result_list);

        setUpMovesModels();

        ListedMoveRecyclerViewAdapter adapter = new ListedMoveRecyclerViewAdapter(requireContext(), listedMoveModels, this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return rootView;
    }

    private void configureBackButton(View rootView) {
        ImageView backButton = rootView.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
    }

    private void setUpMovesModels() {
        String[] movesNames = getResources().getStringArray(R.array.moves_name_arraylist);
        String[] movesTypes = getResources().getStringArray(R.array.moves_type_arraylist);
        int pokemonRankImage = R.drawable.rankone;

        for (int i = 0; i < movesNames.length; i++) {
            listedMoveModels.add(new ListedMoveModel(movesNames[i], movesTypes[i], pokemonRankImage));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireContext(), MoveInfo.class);
        intent.putExtra("move_name", listedMoveModels.get(position).getMoveName());
        startActivity(intent);
    }
}
