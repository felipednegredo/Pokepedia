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
import com.google.firebase.storage.StreamDownloadTask;

import java.io.InputStream;

public class PokemonInfoStatsFragment extends Fragment {

    private static final String ARG_POKEMON_ID = "pokemon_id";

    private String pokemonId;
    private ImageView pokemonImageView;
    private TextView tipoTextView;
    private TextView tipo2TextView;
    private TextView vitalidadeValorTextView;
    private TextView defesaValorTextView;
    private TextView ataqueValorTextView;
    private TextView vidaBaseValorTextView;
    private TextView energiaBaseValorTextView;
    private TextView estagioEvolutivoValorTextView;
    private TextView ritmoEvolutivoValorTextView;
    private TextView habilidadeValorTextView;

    public PokemonInfoStatsFragment() {
        // Required empty public constructor
    }

    public static PokemonInfoStatsFragment newInstance(String pokemonId) {
        PokemonInfoStatsFragment fragment = new PokemonInfoStatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_POKEMON_ID, pokemonId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pokemonId = getArguments().getString(ARG_POKEMON_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pokemon_info_stats, container, false);

        pokemonImageView = rootView.findViewById(R.id.imgPokemon);
        tipoTextView = rootView.findViewById(R.id.tipoText);
        tipo2TextView = rootView.findViewById(R.id.tipoText2);
        vitalidadeValorTextView = rootView.findViewById(R.id.vitalidadeValor);
        defesaValorTextView = rootView.findViewById(R.id.defesaValor);
        ataqueValorTextView = rootView.findViewById(R.id.ataqueValor);
        vidaBaseValorTextView = rootView.findViewById(R.id.vida_base_valor);
        energiaBaseValorTextView = rootView.findViewById(R.id.energia_base_valor);
        estagioEvolutivoValorTextView = rootView.findViewById(R.id.estagio_evolutivo_valor);
        ritmoEvolutivoValorTextView = rootView.findViewById(R.id.ritmo_evolutivo_valor);
        habilidadeValorTextView = rootView.findViewById(R.id.habilidadeValor);

        // Get a reference to the Firebase Storage instance
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a reference to the image file in Firebase Storage
        StorageReference storageRef = storage.getReference().child("pokemons/" + pokemonId + ".jpg");

        // Download the image file as a stream
        storageRef.getStream()
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

        // Retrieve and set the data from Firestore
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
                            String vitalidadeValor = documentSnapshot.getString("vitalidadeValor");
                            String defesaValor = documentSnapshot.getString("defesaValor");
                            String ataqueValor = documentSnapshot.getString("ataqueValor");
                            String vidaBaseValor = documentSnapshot.getString("vida_base_valor");
                            String energiaBaseValor = documentSnapshot.getString("energia_base_valor");
                            String estagioEvolutivoValor = documentSnapshot.getString("estagio_evolutivo_valor");
                            String ritmoEvolutivoValor = documentSnapshot.getString("ritmo_evolutivo_valor");
                            String habilidadeValor = documentSnapshot.getString("habilidadeValor");

                            // Set the data to the respective TextViews
                            tipoTextView.setText(tipo);
                            tipo2TextView.setText(tipo2);
                            vitalidadeValorTextView.setText(vitalidadeValor);
                            defesaValorTextView.setText(defesaValor);
                            ataqueValorTextView.setText(ataqueValor);
                            vidaBaseValorTextView.setText(vidaBaseValor);
                            energiaBaseValorTextView.setText(energiaBaseValor);
                            estagioEvolutivoValorTextView.setText(estagioEvolutivoValor);
                            ritmoEvolutivoValorTextView.setText(ritmoEvolutivoValor);
                            habilidadeValorTextView.setText(habilidadeValor);
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

        return rootView;
    }
}
