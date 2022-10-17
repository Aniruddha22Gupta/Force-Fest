package in.aniruddhag.project8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public String Username, Password;
    public FBDatabase fbDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText txtin_UsID = findViewById(R.id.txtin_UsID);
        EditText txtin_UsPass = findViewById(R.id.txtin_UsPass);
        Button btn_LI = findViewById(R.id.btn_LI);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        txtin_UsID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sEditable;
                sEditable = editable.toString();
                if (!sEditable.isEmpty()){
                    if (!Patterns.EMAIL_ADDRESS.matcher(sEditable).matches()) {
                        txtin_UsID.setError("Wrong Email Format");
                    }
                }
            }
        });

        txtin_UsPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sEditable;
                sEditable = editable.toString();
                if (!sEditable.isEmpty()){
                    if (sEditable.length() != 8) {
                        txtin_UsPass.setError("Password should be at least of 8 characters.");
                    }
                }
            }
        });

        btn_LI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Username = txtin_UsID.getText().toString();
                Password = txtin_UsPass.getText().toString();

                if (Patterns.EMAIL_ADDRESS.matcher(Username).matches()) {
                    mAuth.signInWithEmailAndPassword(Username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LogIn.this, "Successfully Signed In.", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LogIn.this, LandingPage.class));
                            } else {
                                Toast.makeText(LogIn.this, "Couldn't Successfully SignIn. Error: " + task.getException(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void click1(View view) {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}