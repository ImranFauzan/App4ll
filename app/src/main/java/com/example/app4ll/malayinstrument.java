package com.example.app4ll;

import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class malayinstrument extends Fragment {

    private MediaPlayer mediaPlayer;

    public malayinstrument() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_malayinstrument, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Info Buttons Logic
        setupInfoButtons(view);

        // 2. Play Sample Buttons Logic
        setupPlayButtons(view);
    }

    private void setupInfoButtons(View view) {
        ImageButton btnGendangInfo = view.findViewById(R.id.btnGendangInfo);
        ImageButton btnKompangInfo = view.findViewById(R.id.btnKompangInfo);
        ImageButton btnRebanaInfo = view.findViewById(R.id.btnRebanaInfo);
        ImageButton btnMarwasInfo = view.findViewById(R.id.btnMarwasInfo);

        if (btnGendangInfo != null) {
            btnGendangInfo.setOnClickListener(v -> showInfoDialog("Gendang Materials", 
                "Wood for the drum body, animal skin such as goat skin for the drum head and rattan to tighten and hold the skin"));
        }

        if (btnKompangInfo != null) {
            btnKompangInfo.setOnClickListener(v -> showInfoDialog("Kompang Materials", 
                "Wood for the circular frame, animal skin commonly goat skin for the drum surface and metal nails to secure the skin to the frame."));
        }

        if (btnRebanaInfo != null) {
            btnRebanaInfo.setOnClickListener(v -> showInfoDialog("Rebana Materials", 
                "Typically made from hardwood like jackfruit or meranti wood, with a surface made of goat skin tightened using a rattan ring."));
        }

        if (btnMarwasInfo != null) {
            btnMarwasInfo.setOnClickListener(v -> showInfoDialog("Marwas Materials", 
                "A small double-sided drum made from wood and animal skin, traditionally goat or camel skin, laced with thick string."));
        }
    }

    private void setupPlayButtons(View view) {
        Button btnGendangPlay = view.findViewById(R.id.btnGendangPlay);
        Button btnKompangPlay = view.findViewById(R.id.btnKompangPlay);
        Button btnRebanaPlay = view.findViewById(R.id.btnRebanaPlay);
        Button btnMarwasPlay = view.findViewById(R.id.btnMarwasPlay);

        if (btnGendangPlay != null) {
            btnGendangPlay.setOnClickListener(v -> playSound(R.raw.gendang));
        }

        if (btnKompangPlay != null) {
            btnKompangPlay.setOnClickListener(v -> playSound(R.raw.kompang));
        }

        if (btnRebanaPlay != null) {
            btnRebanaPlay.setOnClickListener(v -> playSound(R.raw.rebana));
        }

        if (btnMarwasPlay != null) {
            btnMarwasPlay.setOnClickListener(v -> playSound(R.raw.marwas));
        }
    }

    private void playSound(int rawResourceId) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        try {
            mediaPlayer = MediaPlayer.create(getContext(), rawResourceId);
            if (mediaPlayer != null) {
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(MediaPlayer::release);
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error playing sound", Toast.LENGTH_SHORT).show();
        }
    }

    private void showInfoDialog(String title, String message) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}