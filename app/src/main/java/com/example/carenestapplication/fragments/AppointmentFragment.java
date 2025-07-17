package com.example.carenestapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carenestapplication.R;
import com.example.carenestapplication.adapters.AppointmentAdapter;
import com.example.carenestapplication.models.Appointment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class AppointmentFragment extends Fragment {

    RecyclerView appointmentsRecycler;
    FloatingActionButton addAppointmentBtn;

    public AppointmentFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);

        appointmentsRecycler = view.findViewById(R.id.appointmentsRecycler);
        addAppointmentBtn = view.findViewById(R.id.addAppointmentBtn);

        addAppointmentBtn.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Appointment clicked", Toast.LENGTH_SHORT).show();
            // TODO: Open BottomSheetDialogFragment for adding appointment
        });

        List<Appointment> dummyAppointments = Arrays.asList(
                new Appointment(
                        1,
                        1,
                        "Cardiologist",
                        "July 15, 2025",
                        "10:30 AM",
                        "Dr. Emily Parker",
                        "Saint John Hospital",
                        "Bring past reports",
                        "2025-07-01T10:00:00"
                )

        );

        appointmentsRecycler.setAdapter(new AppointmentAdapter(dummyAppointments));
        return view;
    }
}
