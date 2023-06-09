package com.example.jritev4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    private EditText usernameEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private FirebaseAuth firebaseAuth;
    DatabaseReference usersRef = FirebaseDatabase.getInstance("https://jritev4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        firebaseAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                registerUser(username, email, password);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            Intent intent = new Intent(RegistrationActivity.this, HomepageActivity.class);
            startActivity(intent);
        }
    }
    private void registerUser(String username, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User registration successful
                            saveUserProfile(username);
                        } else {
                            // User registration failed
                            Toast.makeText(RegistrationActivity.this,
                                    "Registration failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserProfile(String username) {
        // Get the currently logged in user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            // Update the user's display name with the provided username
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Profile saved successfully
                                user user1 = new user(user.getUid(), username, emailEditText.getText().toString().trim(), 0.0 );
                                Toast.makeText(RegistrationActivity.this,
                                        "Registration successful",
                                        Toast.LENGTH_SHORT).show();
                                usersRef.child(user.getUid()).setValue(user1)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Data saved successfully
                                                // Perform any additional operations or show success message to the user
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Error occurred while saving data
                                                // Handle the error or show an error message to the user
                                            }
                                        });
                                // Redirect to the main activity or login screen
                                // Example: startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            } else {
                                // Profile save failed
                                Toast.makeText(RegistrationActivity.this,
                                        "Profile save failed: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
