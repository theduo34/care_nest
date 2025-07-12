package com.example.carenestapplication.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carenestapplication.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;

public class FirebaseUtils { 

    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    // Login method
    public static void loginUser(Activity activity, EditText inputEmail, EditText inputPassword) {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Password is required");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Toast.makeText(activity, "Login successful", Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(activity, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public static void registerUser(
            Activity activity,
            EditText inputFullName,
            EditText inputEmail,
            EditText inputPhone,
            EditText inputPassword,
            EditText inputConfirmPassword
    ) {
        String fullName = inputFullName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String phone = inputPhone.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String confirmPassword = inputConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(fullName)) {
            inputFullName.setError("Full name is required");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            inputPhone.setError("Phone number is required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Password is required");
            return;
        }
        if (!password.equals(confirmPassword)) {
            inputConfirmPassword.setError("Passwords do not match");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    String userId = mAuth.getCurrentUser().getUid();

                    // Store profile info in Firestore
                    FirebaseFirestore.getInstance().collection("users").document(userId)
                            .set(new HashMap<String, Object>() {{
                                put("fullName", fullName);
                                put("email", email);
                                put("phone", phone);
                                put("createdAt", new Date());
                            }})
                            .addOnSuccessListener(unused -> {
                                Toast.makeText(activity, "Account created successfully", Toast.LENGTH_SHORT).show();
                                activity.startActivity(new Intent(activity, MainActivity.class));
                                activity.finish();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(activity, "Failed to save profile: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(activity, "Signup failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    // Logout method
    public static void logoutUser(Activity activity) {
        mAuth.signOut();
        Toast.makeText(activity, "Logged out", Toast.LENGTH_SHORT).show();
        // Optionally redirect to Login
    }

    public static FirebaseAuth getAuth() {
        return mAuth;
    }
}
