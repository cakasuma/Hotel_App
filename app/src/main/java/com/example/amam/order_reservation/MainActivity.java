package com.example.amam.order_reservation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;


public class MainActivity extends AppCompatActivity {



    private MaterialEditText mLogEmail;
    private MaterialEditText mLogPass;
    private Button mLogButton;
    private Button btnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mLogButton = (Button) findViewById(R.id.logButton);
        mLogEmail = (MaterialEditText) findViewById(R.id.logEmail);
        mLogPass = (MaterialEditText) findViewById(R.id.logPassword);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SingUpActivity.class));

            }
        });

        mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Loading..", Toast.LENGTH_SHORT).show();
                if(!validateForm()){
                    return;
                }
                
                mAuth.signInWithEmailAndPassword(mLogEmail.getText().toString(), mLogPass.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mLogEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mLogEmail.setError("Required.");
            valid = false;
        } else {
            mLogEmail.setError(null);
        }

        String password = mLogPass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mLogPass.setError("Required.");
            valid = false;
        } else {
            if(password.length() < 6){
                mLogPass.setError("min 6 characters");

            }
            else {
                mLogPass.setError(null);
            }

        }

        return valid;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    private void updateUI(FirebaseUser user){
        if(user!=null){
            Intent myIntent = new Intent(this, OrderMenu.class);
            startActivity(myIntent);
            mLogEmail.setText("");
            mLogPass.setText("");
            finish();
        }
        else{
            mLogEmail.setText("");
            mLogPass.setText("");
        }

    }
}
