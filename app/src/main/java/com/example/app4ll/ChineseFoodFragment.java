package com.example.app4ll;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class ChineseFoodFragment extends Fragment {

    // Name of the file that saved favoritefood
    private static final String PREFS_NAME = "FoodFavorites";

    // UI variables for the "My Favorites" section
    private LinearLayout layoutFavoritesSection;
    private CardView cardMeeTarikFav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chinesefood, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Discover Button
        Button btnMeeTarik = view.findViewById(R.id.btnDiscoverMee);
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

        // Add to favorite button
        // 1. Find the Views
        layoutFavoritesSection = view.findViewById(R.id.layoutFavoritesSection);
        cardMeeTarikFav = view.findViewById(R.id.cardMeeTarikFav);

        // Main heart buttons
        CheckBox btnFavMee= view.findViewById(R.id.btnFavMeeTarik);
        CheckBox btnFavNA = view.findViewById(R.id.btnFavNA);
        CheckBox btnFavDimsum = view.findViewById(R.id.btnFavDimsum);

        Context context = view.getContext();

        if (context != null) {
            setupFavoriteLogic(context, btnFavMee, cardMeeTarikFav, "fav_mee", "Mee Tarik");

            setupSimpleSave(context, btnFavNA, "fav_NA", "Nasi Ayam");
            setupSimpleSave(context, btnFavDimsum, "fav_dimsum", "Dimsum");
        }

        updateSectionVisibility();
    }


    // Helper methods
    private void setupFavoriteLogic(Context context, CheckBox heartButton, final CardView hiddenCard, final String key, final String foodName) {
        // Use the context passed in to get SharedPreferences
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);

        boolean isFavorite = settings.getBoolean(key, false);
        heartButton.setChecked(isFavorite);

        if (isFavorite) {
            hiddenCard.setVisibility(View.VISIBLE);
        } else {
            hiddenCard.setVisibility(View.GONE);
        }

        heartButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(key, isChecked);
            editor.apply();

            if (isChecked) {
                hiddenCard.setVisibility(View.VISIBLE);
                Toast.makeText(context, foodName + " added to Favorites! ❤️", Toast.LENGTH_SHORT).show();
            } else {
                hiddenCard.setVisibility(View.GONE);
                Toast.makeText(context, "Removed " + foodName, Toast.LENGTH_SHORT).show();
            }
            updateSectionVisibility();
        });
    }

    private void setupSimpleSave(Context context, CheckBox heartButton, final String key, final String foodName) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        boolean isFavorite = settings.getBoolean(key, false);
        heartButton.setChecked(isFavorite);

        heartButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(key, isChecked);
            editor.apply();

            if (isChecked) {
                Toast.makeText(context, foodName + " Liked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSectionVisibility() {
        if (cardMeeTarikFav == null) return; // Safety check

        boolean isMeeVisible = (cardMeeTarikFav.getVisibility() == View.VISIBLE);

        // Add checks for RotiCanai or Beriyani here later:
        // boolean isNAVisible = (cardNAFav.getVisibility() == View.VISIBLE);
        if (isMeeVisible) {
            layoutFavoritesSection.setVisibility(View.VISIBLE);
        } else {
            layoutFavoritesSection.setVisibility(View.GONE);
        }


    }
}