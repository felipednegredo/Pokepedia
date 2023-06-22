package mobile.pokepedia;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class PokemonModel {

    private String pokemonNumber;
    private String pokemonType1;
    private String pokemonType2;
    private String pokemonName;
    private int pokemonRank;
    private String pokemonDescription;
    private String pokemonAttack;
    private String pokemonDefense;
    private String pokemonSpeed;
    private String pokemonVitality;
    private String pokemonBaseLife;
    private String pokemonBaseEnergy;
    private String pokemonStageEvolution;
    private String pokemonRhythmEvolution;

    private MoveModel[] pokemonMoves;

    public PokemonModel() {
        // Required empty constructor for Firestore
    }

    public String getPokemonNumber() {
        return pokemonNumber;
    }

    public void setPokemonNumber(String pokemonNumber) {
        this.pokemonNumber = pokemonNumber;
    }

    public String getPokemonType1() {
        return pokemonType1;
    }

    public void setPokemonType1(String pokemonType1) {
        this.pokemonType1 = pokemonType1;
    }

    public String getPokemonType2() {
        return pokemonType2;
    }

    public void setPokemonType2(String pokemonType2) {
        this.pokemonType2 = pokemonType2;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public int getPokemonRank() {
        return pokemonRank;
    }

    public void setPokemonRank(int pokemonRank) {
        this.pokemonRank = pokemonRank;
    }

    public String getPokemonDescription() {
        return pokemonDescription;
    }

    public void setPokemonDescription(String pokemonDescription) {
        this.pokemonDescription = pokemonDescription;
    }

    public String getPokemonAttack() {
        return pokemonAttack;
    }

    public void setPokemonAttack(String pokemonAttack) {
        this.pokemonAttack = pokemonAttack;
    }

    public String getPokemonDefense() {
        return pokemonDefense;
    }

    public void setPokemonDefense(String pokemonDefense) {
        this.pokemonDefense = pokemonDefense;
    }

    public String getPokemonSpeed() {
        return pokemonSpeed;
    }

    public void setPokemonSpeed(String pokemonSpeed) {
        this.pokemonSpeed = pokemonSpeed;
    }

    public String getPokemonVitality() {
        return pokemonVitality;
    }

    public void setPokemonVitality(String pokemonVitality) {
        this.pokemonVitality = pokemonVitality;
    }

    public String getPokemonBaseLife() {
        return pokemonBaseLife;
    }

    public void setPokemonBaseLife(String pokemonBaseLife) {
        this.pokemonBaseLife = pokemonBaseLife;
    }

    public String getPokemonBaseEnergy() {
        return pokemonBaseEnergy;
    }

    public void setPokemonBaseEnergy(String pokemonBaseEnergy) {
        this.pokemonBaseEnergy = pokemonBaseEnergy;
    }

    public String getPokemonStageEvolution() {
        return pokemonStageEvolution;
    }

    public void setPokemonStageEvolution(String pokemonStageEvolution) {
        this.pokemonStageEvolution = pokemonStageEvolution;
    }

    public String getPokemonRhythmEvolution() {
        return pokemonRhythmEvolution;
    }

    public void setPokemonRhythmEvolution(String pokemonRhythmEvolution) {
        this.pokemonRhythmEvolution = pokemonRhythmEvolution;
    }

    public MoveModel[] getPokemonMoves() {
        return pokemonMoves;
    }

    public void setPokemonMoves(MoveModel[] pokemonMoves) {
        this.pokemonMoves = pokemonMoves;
    }

    public void savePokemon() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference pokemonCollection = db.collection("pokemon");
        DocumentReference pokemonDocument = pokemonCollection.document(this.pokemonNumber);
        pokemonDocument.set(this)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Pokemon saved successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Pokemon save failed");
                    }
                });
    }

    public void deletePokemon() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference pokemonCollection = db.collection("pokemon");
        DocumentReference pokemonDocument = pokemonCollection.document(this.pokemonNumber);
        pokemonDocument.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Pokemon deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Pokemon delete failed");
                    }
                });
    }

    public void updatePokemon() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference pokemonCollection = db.collection("pokemon");
        DocumentReference pokemonDocument = pokemonCollection.document(this.pokemonNumber);
        pokemonDocument.update(
                "pokemonNumber", this.pokemonNumber,
                "pokemonType1", this.pokemonType1,
                "pokemonType2", this.pokemonType2,
                "pokemonName", this.pokemonName,
                "pokemonRank", this.pokemonRank,
                "pokemonDescription", this.pokemonDescription,
                "pokemonAttack", this.pokemonAttack,
                "pokemonDefense", this.pokemonDefense,
                "pokemonSpeed", this.pokemonSpeed,
                "pokemonVitality", this.pokemonVitality,
                "pokemonBaseLife", this.pokemonBaseLife,
                "pokemonBaseEnergy", this.pokemonBaseEnergy,
                "pokemonStageEvolution", this.pokemonStageEvolution,
                "pokemonRhythmEvolution", this.pokemonRhythmEvolution,
                "pokemonMoves", this.pokemonMoves
        );
    }

    public void getPokemon(String pokemonNumber) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference pokemonCollection = db.collection("pokemon");
        DocumentReference pokemonDocument = pokemonCollection.document(pokemonNumber);
        pokemonDocument.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        PokemonModel pokemon = documentSnapshot.toObject(PokemonModel.class);
                        System.out.println("Pokemon retrieved successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Pokemon retrieve failed");
                    }
                });
    }

    public void getAllPokemon() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference pokemonCollection = db.collection("pokemon");
        pokemonCollection.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<PokemonModel> pokemonList = queryDocumentSnapshots.toObjects(PokemonModel.class);
                        System.out.println("Pokemon retrieved successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Pokemon retrieve failed");
                    }
                });
    }

    public void getPokemonByName(String pokemonName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference pokemonCollection = db.collection("pokemon");
        Query pokemonQuery = pokemonCollection.whereEqualTo("pokemonName", pokemonName);
        pokemonQuery.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<PokemonModel> pokemonList = queryDocumentSnapshots.toObjects(PokemonModel.class);
                        System.out.println("Pokemon retrieved successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Pokemon retrieve failed");
                    }
                });
    }

    public void getPokemonByID(String pokemonNumber) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference pokemonCollection = db.collection("pokemon");
        Query pokemonQuery = pokemonCollection.whereEqualTo("pokemonNumber", pokemonNumber);
        pokemonQuery.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<PokemonModel> pokemonList = queryDocumentSnapshots.toObjects(PokemonModel.class);
                        System.out.println("Pokemon retrieved successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Pokemon retrieve failed");
                    }
                });
    }

    public int getNumber() {
        return Integer.parseInt(this.pokemonNumber);
    }

    public String getName() {
        return this.pokemonName;
    }
}
