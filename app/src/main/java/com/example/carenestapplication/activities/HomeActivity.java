package com.example.carenestapplication.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.carenestapplication.R;
import com.example.carenestapplication.fragments.AppointmentFragment;
import com.example.carenestapplication.fragments.DashboardFragment;
import com.example.carenestapplication.fragments.JournalFragment;
import com.example.carenestapplication.fragments.MedicationFragment;
import com.example.carenestapplication.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    ImageView profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottomNav);
        profileIcon = findViewById(R.id.profileIcon);

        loadFragment(new DashboardFragment());

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            int id = item.getItemId();

            if (id == R.id.nav_dashboard) {
                selected = new DashboardFragment();
            } else if (id == R.id.nav_appointment) {
                selected = new AppointmentFragment();
            } else if (id == R.id.nav_journal) {
                selected = new JournalFragment();
            } else if (id == R.id.nav_medication) {
                selected = new MedicationFragment();
            }

            if (selected != null) loadFragment(selected);
            return true;
        });

        profileIcon.setOnClickListener(v -> loadFragment(new ProfileFragment()));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
