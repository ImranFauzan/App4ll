package com.example.app4ll;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChineseInstrumentFragment extends Fragment {

    private MediaPlayer guzheng, sheng, pipa, dizi;

    public ChineseInstrumentFragment() {
        super(R.layout.fragment_chineseinstrument);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize MediaPlayers
        guzheng = MediaPlayer.create(getContext(), R.raw.guzheng);
        sheng   = MediaPlayer.create(getContext(), R.raw.sheng);
        pipa    = MediaPlayer.create(getContext(), R.raw.pipa);
        dizi    = MediaPlayer.create(getContext(), R.raw.dizi);

        // Find buttons
        Button btnGuzheng = view.findViewById(R.id.button);
        Button btnSheng   = view.findViewById(R.id.button2);
        Button btnPipa    = view.findViewById(R.id.button3);
        Button btnDizi    = view.findViewById(R.id.button4);

        // Set toggle listeners
        setupToggleButton(btnGuzheng, guzheng);
        setupToggleButton(btnSheng, sheng);
        setupToggleButton(btnPipa, pipa);
        setupToggleButton(btnDizi, dizi);
    }

    // Helper method to toggle play/stop
    private void setupToggleButton(Button button, MediaPlayer player) {
        button.setOnClickListener(v -> {
            if (player.isPlaying()) {
                player.pause();  // stop/pause playback
                player.seekTo(0); // reset to start
                button.setText("PLAY");
            } else {
                player.start();   // start playback
                button.setText("STOP");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (guzheng != null) guzheng.release();
        if (sheng != null) sheng.release();
        if (pipa != null) pipa.release();
        if (dizi != null) dizi.release();
    }
}
