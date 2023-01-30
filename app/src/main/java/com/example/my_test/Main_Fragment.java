package com.example.my_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_test.Main.Provile_Fragment;
import com.example.my_test.Main.TimeTable_Fragment;


public class Main_Fragment extends Fragment {
    private View fragment;
    private Button profile,timetable;


    public Main_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragment = view.findViewById(R.id.time_profile_fragment);
        profile = view.findViewById(R.id.profile);
        timetable=view.findViewById(R.id.timetable);
        if (savedInstanceState == null) {
            TimeTable_Fragment timeTable_fragment = new TimeTable_Fragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.time_profile_fragment, timeTable_fragment, "ti")
                    .commit();
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Provile_Fragment provile_fragment = new Provile_Fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.time_profile_fragment, provile_fragment, "tit")
                        .commit();
            }
        });

        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeTable_Fragment timeTable_fragment = new TimeTable_Fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.time_profile_fragment, timeTable_fragment, "tfit")
                        .commit();
            }
        });
    }
}