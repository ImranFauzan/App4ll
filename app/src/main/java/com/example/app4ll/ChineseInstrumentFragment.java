package com.example.app4ll;

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

public class ChineseInstrumentFragment extends Fragment {

    private MediaPlayer guzheng, sheng, pipa, dizi;

    public ChineseInstrumentFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_chineseinstrument, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        guzheng = MediaPlayer.create(getContext(), R.raw.guzheng);
        sheng   = MediaPlayer.create(getContext(), R.raw.sheng);
        pipa    = MediaPlayer.create(getContext(), R.raw.pipa);
        dizi    = MediaPlayer.create(getContext(), R.raw.dizi);

        Button btnGuzheng = view.findViewById(R.id.button);
        Button btnSheng   = view.findViewById(R.id.button2);
        Button btnPipa    = view.findViewById(R.id.button3);
        Button btnDizi    = view.findViewById(R.id.button4);

        setupToggleButton(btnGuzheng, guzheng);
        setupToggleButton(btnSheng, sheng);
        setupToggleButton(btnPipa, pipa);
        setupToggleButton(btnDizi, dizi);

        ImageButton infoGuzheng = view.findViewById(R.id.imageButton);
        ImageButton infoSheng   = view.findViewById(R.id.imageButton2);
        ImageButton infoPipa    = view.findViewById(R.id.imageButton3);
        ImageButton infoDizi    = view.findViewById(R.id.imageButton4);

        infoGuzheng.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Guzheng is made of wood with steel or nylon strings.",
                        Toast.LENGTH_LONG).show());

        infoSheng.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Sheng is made of bamboo pipes with metal reeds.",
                        Toast.LENGTH_LONG).show());

        infoPipa.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Pipa is made of wood with four strings.",
                        Toast.LENGTH_LONG).show());

        infoDizi.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Dizi is made of bamboo with a vibrating membrane.",
                        Toast.LENGTH_LONG).show());
    }

    private void setupToggleButton(Button button, MediaPlayer player) {
        button.setOnClickListener(v -> {
            if (player.isPlaying()) {
                player.pause();
                player.seekTo(0);
                button.setText("PLAY");
            } else {
                player.start();
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
