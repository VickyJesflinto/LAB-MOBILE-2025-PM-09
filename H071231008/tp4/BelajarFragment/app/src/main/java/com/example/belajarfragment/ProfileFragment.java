package com.example.belajarfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView NameProfile = view.findViewById(R.id.tv_name);

            Bundle bundle = getArguments();
            if (bundle != null) {
                String name = bundle.getString("name");
                NameProfile.setText(name);
            }
        return view;
    }
}