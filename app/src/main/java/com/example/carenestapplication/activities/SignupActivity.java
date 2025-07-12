package com.example.carenestapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carenestapplication.R;
import com.example.carenestapplication.services.UserService;
import com.example.carenestapplication.models.User;
import com.example.carenestapplication.utils.ToastUtils;

public class SignupActivity extends AppCompatActivity {

    private EditText inputFirstName, inputLastName, inputEmail, inputPassword, inputConfirmPassword, inputPhone;
    Button btnSignup;
    TextView goToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
        String fullText = "Do you have an Account? <u><b><font color='#2E7D32'>Sign in</font></b></u>";
        goToLogin.setText(Html.fromHtml(fullText));


        // Handle sing up button click
        btnSignup.setOnClickListener(v -> {
            String firstName = inputFirstName.getText().toString().trim();
            String lastName = inputLastName.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String phone = inputPhone.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            String confirmPassword = inputConfirmPassword.getText().toString().trim();

            // Validate fields
            if (TextUtils.isEmpty(firstName)) {
                inputFirstName.setError("First name is required");
                return;
            }

            if (TextUtils.isEmpty(lastName)) {
                inputLastName.setError("Last name is required");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                inputEmail.setError("Email is required");
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                inputEmail.setError("Enter a valid email");
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

            if (password.length() < 6) {
                inputPassword.setError("Password must be at least 6 characters");
                return;
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                inputConfirmPassword.setError("Please confirm your password");
                return;
            }

            if (!password.equals(confirmPassword)) {
                inputConfirmPassword.setError("Passwords do not match");
                return;
            }

            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPhoneNumber(phone);
            newUser.setPassword(password);

            UserService userService = new UserService(this);
            boolean registered = userService.registerUser(newUser);

            if (registered) {
                ToastUtils.success(this, "User registered successfully!");
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                ToastUtils.error(this, "User already exists");
            }
        });


        // Navigate to login activity
        goToLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }

    // Instance initialization
    public void init() {
        inputFirstName = findViewById(R.id.inputFirstName);
        inputLastName = findViewById(R.id.inputLastName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputPhone = findViewById(R.id.inputPhone);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);
        goToLogin = findViewById(R.id.goToLogin);

    }
}

