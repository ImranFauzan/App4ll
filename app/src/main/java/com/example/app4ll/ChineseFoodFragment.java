package com.example.app4ll;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ChineseFoodFragment extends Fragment {

    public ChineseFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chinesefood, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Discover Button
        Button btnMeeTarik = view.findViewById(R.id.btnFavMeeTarik);
        Button btnNasiAyam = view.findViewById(R.id.btnDiscoverNA);

        btnMeeTarik.setOnClickListener(v -> {
            String url = "https://share.google/1BfywxTXGHslPHyWk";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
        btnNasiAyam.setOnClickListener(v -> {
            String url = "https://www.cheemeng.com.my/locations.html";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
}}
