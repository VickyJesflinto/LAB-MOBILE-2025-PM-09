package com.example.belajarfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        EditText inputname = view.findViewById(R.id.editName);
        Button buttonsave = view.findViewById((R.id.btnSave));

        buttonsave.setOnClickListener( v -> {
            String name = inputname.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(getActivity(), "Name is required", Toast.LENGTH_SHORT).show();
            } else {
                ProfileFragment profileFragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                profileFragment.setArguments(bundle);

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, profileFragment)
                        .addToBackStack(null)
                        .commit();
            }

        });
        return view;
    }
}