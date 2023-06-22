package mobile.pokepedia;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MoveModel {
    // Implements CRUD operations for moves integrated with Firebase

    private String moveName;
    private String moveType;
    private int moveRank;
    private String description;
    private String move;
    private String range;
    private String target;
    private String cost;

    public MoveModel() {
        // Default constructor required for Firestore
    }

    public MoveModel(String moveName, String moveType, int moveRank, String description, String move, String range, String target, String cost) {
        this.moveName = moveName;
        this.moveType = moveType;
        this.moveRank = moveRank;
        this.description = description;
        this.move = move;
        this.range = range;
        this.target = target;
        this.cost = cost;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public int getMoveRank() {
        return moveRank;
    }

    public void setMoveRank(int moveRank) {
        this.moveRank = moveRank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void saveMove() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference movesCollectionRef = db.collection("moves");
        movesCollectionRef.add(this)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("Move added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Error adding move");
                    }
                });
    }

    public void updateMove(String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference moveRef = db.collection("moves").document(id);
        moveRef.set(this)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Move updated");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Error updating move");
                    }
                });
    }

    public void deleteMove(String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference moveRef = db.collection("moves").document(id);
        moveRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Move deleted");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Error deleting move");
                    }
                });
    }

    public void getMove(String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference moveRef = db.collection("moves").document(id);
        moveRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            MoveModel move = documentSnapshot.toObject(MoveModel.class);
                            System.out.println("Move: " + move);
                        } else {
                            System.out.println("Move not found");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Error getting move");
                    }
                });
    }

    public void getAllMoves() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference movesCollectionRef = db.collection("moves");
        movesCollectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshot) {
                        if (!queryDocumentSnapshot.isEmpty()) {
                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshot.getDocuments()) {
                                MoveModel move = documentSnapshot.toObject(MoveModel.class);
                                System.out.println("Move: " + move);
                            }
                        } else {
                            System.out.println("No moves found");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Error getting moves");
                    }
                });
    }

    public void getMovesByID(String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference movesCollectionRef = db.collection("moves");
        movesCollectionRef.whereEqualTo("id", id).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshot) {
                        if (!queryDocumentSnapshot.isEmpty()) {
                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshot.getDocuments()) {
                                MoveModel move = documentSnapshot.toObject(MoveModel.class);
                                System.out.println("Move: " + move);
                            }
                        } else {
                            System.out.println("No moves found");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Error getting moves");
                    }
                });
    }
}
