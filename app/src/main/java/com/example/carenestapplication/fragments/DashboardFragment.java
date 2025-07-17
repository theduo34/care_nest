package com.example.carenestapplication.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carenestapplication.R;
import com.example.carenestapplication.utils.UserAuthProvider;

public class DashboardFragment extends Fragment {
    UserAuthProvider user = UserAuthProvider.getInstance();

    public DashboardFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("USER" + user.getCurrentUser().getFirstName());

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}

