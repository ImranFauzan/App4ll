package com.example.app4ll;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IndianInstrument#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndianInstrument extends Fragment {

    MediaPlayer mp;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IndianInstrument() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndianInstrument.
     */
    // TODO: Rename and change types and number of parameters
    public static IndianInstrument newInstance(String param1, String param2) {
        IndianInstrument fragment = new IndianInstrument();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indian_instrument, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize button here after view is created
        Button btnSitar = view.findViewById(R.id.btnSitarPlay);
        Button btnTambura = view.findViewById(R.id.btnTamburaPlay);
        Button btnTabla = view.findViewById(R.id.btnTablaPlay);
        Button btnMridangam = view.findViewById(R.id.btnmridangamPlay);
        Button btnBansuri = view.findViewById(R.id.btnBansuriPlay);
        Button btnNadaswaram = view.findViewById(R.id.btnNadaswaramPlay);
        Button btnGhatam = view.findViewById(R.id.btnGhatamPlay);
        Button btnTaal = view.findViewById(R.id.btnTaalPlay);

        btnSitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.sitar);
            }
        });

        btnTambura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.tambura);
            }
        });

        btnTabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.tabla);
            }
        });

        btnMridangam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.mridangam);
            }
        });

        btnBansuri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.bansuri);
            }
        });

        btnNadaswaram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.nadaswaram);
            }
        });

        btnGhatam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.ghatam);
            }
        });

        btnTaal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.taal);
            }
        });





    }

    private void playSound(int soundResourceId) {
        // Release previous MediaPlayer if it exists
        if (mp != null) {
            mp.release();
            mp = null;
        }
        mp = MediaPlayer.create(requireContext(), soundResourceId);

        if (mp != null) {
            mp.start();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                    mp = null;
                }
            });
        }
    }


}
