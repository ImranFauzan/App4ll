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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IndianInstrument#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndianInstrument extends Fragment {

    MediaPlayer mp;
    private MediaPlayer Sitar, Tambura , Tabla , Mridangam, Bansuri, Nadaswaram, Ghatam, Taal;


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
        Sitar = MediaPlayer.create(getContext(), R.raw.sitar);
        Tambura   = MediaPlayer.create(getContext(), R.raw.tambura);
        Tabla    = MediaPlayer.create(getContext(), R.raw.tabla);
        Mridangam  = MediaPlayer.create(getContext(), R.raw.mridangam);
        Bansuri = MediaPlayer.create(getContext(), R.raw.bansuri);
        Nadaswaram   = MediaPlayer.create(getContext(), R.raw.nadaswaram);
        Ghatam    = MediaPlayer.create(getContext(), R.raw.ghatam);
        Taal    = MediaPlayer.create(getContext(), R.raw.taal);

        Button btnSitar = view.findViewById(R.id.btnSitarPlay);
        Button btnTambura = view.findViewById(R.id.btnTamburaPlay);
        Button btnTabla = view.findViewById(R.id.btnTablaPlay);
        Button btnMridangam = view.findViewById(R.id.btnmridangamPlay);
        Button btnBansuri = view.findViewById(R.id.btnBansuriPlay);
        Button btnNadaswaram = view.findViewById(R.id.btnNadaswaramPlay);
        Button btnGhatam = view.findViewById(R.id.btnGhatamPlay);
        Button btnTaal = view.findViewById(R.id.btnTaalPlay);

        setupToggleButton(btnSitar, Sitar);
        setupToggleButton(btnTambura, Tambura);
        setupToggleButton(btnTabla, Tabla);
        setupToggleButton(btnMridangam, Mridangam);
        setupToggleButton(btnBansuri, Bansuri);
        setupToggleButton(btnNadaswaram, Nadaswaram);
        setupToggleButton(btnGhatam, Ghatam);
        setupToggleButton(btnTaal, Taal);

        ImageButton infoSitar = view.findViewById(R.id.btnSitarInfo);
        ImageButton infoTambura = view.findViewById(R.id.btnTamburaInfo);
        ImageButton infoTabla = view.findViewById(R.id.btnTablaInfo);
        ImageButton infoMridangam = view.findViewById(R.id.btnMridangamInfo);
        ImageButton infoBansuri = view.findViewById(R.id.btnbansuriInfo);
        ImageButton infoNadaswaram = view.findViewById(R.id.btnNadaswaramInfo);
        ImageButton infoGhatam = view.findViewById(R.id.btnghatamInfo);
        ImageButton infoTaal = view.findViewById(R.id.btntaalInfo);

        infoSitar.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Sitar body is made from Tun wood. It strings is made from metal strings.",
                        Toast.LENGTH_LONG).show());

        infoTambura.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Tambura is made from jakewood or teak.",
                        Toast.LENGTH_LONG).show());

        infoTabla.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Tabla: Dayan is carved from heave rosewood while Vayan is typically made out of metal",
                        Toast.LENGTH_LONG).show());

        infoMridangam.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Mridangam body is a hollowed-out block of Jackfruit wood. The two heads are layers of goat and cow skin, held together by buffalo-hide leather straps.",
                        Toast.LENGTH_LONG).show());

        infoBansuri.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Bansuri is Traditionally made from a single length of special straight-growth bamboo.",
                        Toast.LENGTH_LONG).show());

        infoNadaswaram.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Nadaswaram body is made of Ebony or other dark hardwoods to withstand high pressure.",
                        Toast.LENGTH_LONG).show());

        infoGhatam.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Ghatam is made from clay mixed with iron, copper, and brass filings. This metallic mixture gives it its sharp, bell-like \"ringing\" tone..",
                        Toast.LENGTH_LONG).show());

        infoTaal.setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Taal is Small hand cymbals made of bell metal, which is a specific alloy of bronze, brass, or copper.",
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
        if (Sitar != null) Sitar.release();
        if (Tambura != null) Tambura.release();
        if (Tabla != null) Tabla.release();
        if (Mridangam != null) Mridangam.release();
        if (Bansuri != null) Bansuri.release();
        if (Nadaswaram != null) Nadaswaram.release();
        if (Ghatam != null) Ghatam.release();
        if (Taal != null) Taal.release();
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }
}
