package com.learn.app_vs_wild;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    RecyclerView data;
    List<String> titreARecuperer;
    List<Integer> imageARecuperer;
    AdapteurRecyclerview adapteurRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        data = findViewById(R.id.recyclerview);
        titreARecuperer = new ArrayList<>();
        imageARecuperer = new ArrayList<>();

        titreARecuperer.add("TEST 1");
        titreARecuperer.add("TEST 2");
        titreARecuperer.add("TEST 3");
        titreARecuperer.add("TEST 4");
        titreARecuperer.add("TEST 5");
        titreARecuperer.add("TEST 6");
        titreARecuperer.add("TEST 7");
        titreARecuperer.add("TEST 8");
        titreARecuperer.add("TEST 9");


        imageARecuperer.add(R.drawable.canned);
        imageARecuperer.add(R.drawable.fire);
        imageARecuperer.add(R.drawable.mushrooms);
        imageARecuperer.add(R.drawable.path);
        imageARecuperer.add(R.drawable.snowdrop);
        imageARecuperer.add(R.drawable.thermometer);
        imageARecuperer.add(R.drawable.watter);
        imageARecuperer.add(R.drawable.wind);
        imageARecuperer.add(R.drawable.owl);

        adapteurRecyclerview = new AdapteurRecyclerview(this, titreARecuperer, imageARecuperer);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        data.setLayoutManager(gridLayoutManager);
        data.setAdapter(adapteurRecyclerview);
    }
}
