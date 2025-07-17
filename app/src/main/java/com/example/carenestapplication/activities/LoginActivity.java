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
import com.example.carenestapplication.models.User;
import com.example.carenestapplication.models.UserProfile;
import com.example.carenestapplication.services.UserProfileService;
import com.example.carenestapplication.services.UserService;
import com.example.carenestapplication.utils.ToastUtils;
import com.example.carenestapplication.utils.UserAuthProvider;

public class LoginActivity extends AppCompatActivity {
    UserService userService = new UserService(this);
    UserProfileService userProfileService = new UserProfileService(this);

    private EditText inputEmail, inputPassword;
    Button btnLogin;
    TextView goToSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        String fullText = "Don't have an account? <u><b><font color='#2E7D32'>Register now</font></b></u>";
        goToSignup.setText(Html.fromHtml(fullText));


        btnLogin.setOnClickListener(v -> {
            String email = inputEmail.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)) {
                inputEmail.setError("Email is require!");
                return;
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                inputEmail.setError("Enter a valid email");
                return;
            }
            if(TextUtils.isEmpty(password)) {
                inputPassword.setError("Password is required!");
            }
            if (password.length() < 6) {
                inputPassword.setError("Password must be at least 6 characters");
                return;
            }

            var results = userService.loginUser(email, password);
            if(results) {
                User loggedInUser = userService.getUserByEmail(email);
                UserProfile profile = userProfileService.getProfileByUserId(loggedInUser.getUserId());

                UserAuthProvider authProvider = UserAuthProvider.getInstance();
                authProvider.setCurrentUser(loggedInUser);
                authProvider.setCurrentUserProfile(profile);

                ToastUtils.success(this, "Login successfully");
                startActivity(new Intent(this, HomeActivity.class));
            } else {
                ToastUtils.error(this, "Invalid email or password");
            }
        });

        goToSignup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            finish();
        });
    }

    public void init() {
        inputEmail = findViewById(R.id.inputLoginEmail);
        inputPassword = findViewById(R.id.inputLoginPassword);

        btnLogin = findViewById(R.id.btnLogin);
        goToSignup = findViewById(R.id.goToSignup);
    }
}
