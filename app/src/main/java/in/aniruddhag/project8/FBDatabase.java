package in.aniruddhag.project8;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

public class FBDatabase {
    public DataSnapshot dataSnapshot;
    public Exception DEError;

    public DataSnapshot Read (DatabaseReference databaseReference) {
        dataSnapshot = null;
        databaseReference.getRef().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful() && task.getResult().exists()) {
                    dataSnapshot = task.getResult();
                }
            }
        });
        return dataSnapshot;
    }

    public DataSnapshot ConRead (DatabaseReference databaseReference) {
        dataSnapshot = null;
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSnapshot = snapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dataSnapshot = null;
            }
        });
        return dataSnapshot;
    }

    public String Remove (DatabaseReference databaseReference) {
        databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                DEError = task.getException();
            }
        });
        return DEError.getMessage();
    }

    public String Write (DatabaseReference databaseReference, JSONObject oData) {
        databaseReference.setValue(oData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                DEError = task.getException();
            }
        });
        return DEError.getMessage();
    }
}
