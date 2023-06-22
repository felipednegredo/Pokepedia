package mobile.pokepedia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StreamDownloadTask;

import java.io.InputStream;

public class PokemonInfoDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageView pokemonImageView;
    private TextView tipoTextView;
    private TextView tipo2TextView;
    private TextView vitalidadeValorTextView;
    private TextView defesaValorTextView;
    private TextView forcaValorTextView;
    private TextView velocidadeValorTextView;
    private TextView descricaoTextView;
    private StorageTask<StreamDownloadTask.TaskSnapshot> downloadTask;

    public PokemonInfoDetailFragment() {
        // Required empty public constructor
    }

    public static PokemonInfoDetailFragment newInstance(String param1, String param2) {
        PokemonInfoDetailFragment fragment = new PokemonInfoDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_info_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pokemonImageView = view.findViewById(R.id.imgPokemon);
        tipoTextView = view.findViewById(R.id.tipoText);
        tipo2TextView = view.findViewById(R.id.tipoText2);
        vitalidadeValorTextView = view.findViewById(R.id.vitalidadeValor);
        defesaValorTextView = view.findViewById(R.id.defesaValor);
        forcaValorTextView = view.findViewById(R.id.forçaValor);
        velocidadeValorTextView = view.findViewById(R.id.velocidadeValor);
        descricaoTextView = view.findViewById(R.id.descricao);

        // Get the Pokémon ID from the arguments or any other source
        String pokemonId = getArguments().getString(ARG_PARAM1);

        // Get a reference to the Firebase Storage instance
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a reference to the image file in Firebase Storage using the Pokémon ID
        StorageReference storageRef = storage.getReference().child("pokemons/" + pokemonId + ".jpg");

        // Download the image file as a stream
        downloadTask = storageRef.getStream()
                .addOnSuccessListener(new OnSuccessListener<StreamDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(StreamDownloadTask.TaskSnapshot taskSnapshot) {
                        // Convert the stream to a Bitmap
                        InputStream stream = taskSnapshot.getStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(stream);

                        // Set the Bitmap to the ImageView
                        pokemonImageView.setImageBitmap(bitmap);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        // TODO: Handle image download failure
                    }
                });

        // Retrieve and set the remaining data from Firestore
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("pokemon").document(pokemonId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Retrieve the data from the document
                            String tipo = documentSnapshot.getString("tipo");
                            String tipo2 = documentSnapshot.getString("tipo2");
                            String vitalidadeValor = documentSnapshot.getString("vitalidadevalor");
                            String defesaValor = documentSnapshot.getString("defesavalor");
                            String forcaValor = documentSnapshot.getString("forçavalor");
                            String velocidadeValor = documentSnapshot.getString("velocidadevalor");
                            String descricao = documentSnapshot.getString("descrição");

                            // Set the data to the respective TextViews
                            tipoTextView.setText("Tipo: " + tipo);
                            tipo2TextView.setText("Tipo2: " + tipo2);
                            vitalidadeValorTextView.setText("Vitalidade: " + vitalidadeValor);
                            defesaValorTextView.setText("Defesa: " + defesaValor);
                            forcaValorTextView.setText("Força: " + forcaValor);
                            velocidadeValorTextView.setText("Velocidade: " + velocidadeValor);
                            descricaoTextView.setText("Descrição: " + descricao);
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
    public void onDestroyView() {
        super.onDestroyView();

        // Cancel the download task if it's in progress
        if (downloadTask != null && !downloadTask.isComplete()) {
            downloadTask.cancel();
        }
    }
}
