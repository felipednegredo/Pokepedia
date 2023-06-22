package mobile.pokepedia;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;

public class MoveInfoDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private String moveName;
    private String description;
    private String move;
    private String range;
    private String target;
    private String cost;

    public MoveInfoDetailFragment() {
    }

}
