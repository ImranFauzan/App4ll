package com.example.app4ll;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class malay_food extends Fragment {

    private static final String PREFS_NAME = "FoodFavorites";
    private LinearLayout layoutFavoritesSection;
    private CardView cardNasiLemakFav;
    private CardView cardNasiKerabuFav;
    private CardView cardSatayFav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_malay_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Discover Buttons
        Button btnNasiLemak = view.findViewById(R.id.btnDiscoverBurungHantu);
        Button btnNasiKerabu = view.findViewById(R.id.btnDiscoverStesen);
        Button btnSatay = view.findViewById(R.id.btnDiscoverSatay);

        if (btnNasiLemak != null) {
            btnNasiLemak.setOnClickListener(v -> {
                String url = "https://nlbh.my";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            });
        }

        if (btnNasiKerabu != null) {
            btnNasiKerabu.setOnClickListener(v -> {
                String url = "https://stesennasikerabu.com";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            });
        }

        if (btnSatay != null) {
            btnSatay.setOnClickListener(v -> {
                String url = "https://share.google/Y3Bc0dgA5VtPwHzYU";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            });
        }

        // Favorites Section
        layoutFavoritesSection = view.findViewById(R.id.layoutFavoritesSection);
        cardNasiLemakFav = view.findViewById(R.id.cardNasiLemakFav);

        CheckBox btnFavNasiLemak = view.findViewById(R.id.btnFavNasiLemak);
        CheckBox btnFavNasiKerabu = view.findViewById(R.id.btnFavNasiKerabu);
        CheckBox btnFavSatay = view.findViewById(R.id.btnFavSatay);

        Context context = view.getContext();
        if (context != null) {
            setupFavoriteLogic(context, btnFavNasiLemak, cardNasiLemakFav, "fav_nasilemak");
            setupFavoriteLogic(context, btnFavNasiKerabu, cardNasiKerabuFav, "fav_nasikerabu");
            setupFavoriteLogic(context, btnFavSatay, cardSatayFav, "fav_satay");
        }

        updateSectionVisibility();
    }

    private void setupFavoriteLogic(Context context, CheckBox heartButton, final CardView hiddenCard, final String key) {
        if (heartButton == null || hiddenCard == null) return;
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        boolean isFavorite = settings.getBoolean(key, false);
        heartButton.setChecked(isFavorite);
        hiddenCard.setVisibility(isFavorite ? View.VISIBLE : View.GONE);

        heartButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(key, isChecked);
            editor.apply();
            hiddenCard.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateSectionVisibility();
        });
    }

    private void updateSectionVisibility() {
        if (layoutFavoritesSection == null) return;
        boolean isAnyVisible = (cardNasiLemakFav != null && cardNasiLemakFav.getVisibility() == View.VISIBLE) ||
                               (cardNasiKerabuFav != null && cardNasiKerabuFav.getVisibility() == View.VISIBLE) ||
                               (cardSatayFav != null && cardSatayFav.getVisibility() == View.VISIBLE);
        layoutFavoritesSection.setVisibility(isAnyVisible ? View.VISIBLE : View.GONE);
    }
}