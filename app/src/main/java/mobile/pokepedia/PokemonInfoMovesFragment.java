package mobile.pokepedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PokemonInfoMovesFragment extends Fragment implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private ArrayList<ListedMoveModel> listedMoveModels = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pokemon_info_moves, container, false);

        recyclerView = rootView.findViewById(R.id.result_list);

        setUpMovesModels();

        ListedMoveRecyclerViewAdapter adapter = new ListedMoveRecyclerViewAdapter(requireContext(), listedMoveModels, this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return rootView;
    }

    private void setUpMovesModels() {
        // Get the Pokémon ID from the arguments or any other source
        String pokemonId = getArguments().getString("pokemon_id");

        // Retrieve and set the moves for the Pokémon from Firestore
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("pokemon").document(pokemonId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            List<String> movesNames = (List<String>) documentSnapshot.get("movesNames");
                            List<String> movesTypes = (List<String>) documentSnapshot.get("movesTypes");

                            if (movesNames != null && movesTypes != null) {
                                int pokemonRankImage = R.drawable.rankone;

                                for (int i = 0; i < movesNames.size(); i++) {
                                    listedMoveModels.add(new ListedMoveModel(movesNames.get(i), movesTypes.get(i), pokemonRankImage));
                                }

                                // Notify the adapter about the changes
                                if (recyclerView != null && recyclerView.getAdapter() != null) {
                                    recyclerView.getAdapter().notifyDataSetChanged();
                                }
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors
                        // TODO: Handle Firestore data retrieval failure
                    }
                });
    }


    @Override
    public void onResume() {
        super.onResume();
        // Atualiza os dados caso necessário
        listedMoveModels.clear();
        setUpMovesModels();
    }
}
