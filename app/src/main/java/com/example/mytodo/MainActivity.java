package com.example.mytodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Notes> arrNotes;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrNotes = new ArrayList<>();
        LinearLayout layout = findViewById(R.id.frameLL);

        arrNotes.add(new Notes().setTitle("Заметка 1").setDescription("agdfgsdads"));
        arrNotes.add(new Notes().setTitle("Купить хлеб").setDescription("hggasdads"));
        arrNotes.add(new Notes().setTitle("Приготовить еду").setDescription("dfgasdads"));

        for (int i = 0; i < arrNotes.size(); i++) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(arrNotes.get(i).getTitle());
            tv.setTextSize(30);
            tv.setTextColor(ContextCompat.getColor(this,R.color.purple_500));
            layout.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .add(R.id.frame, new NotesFragment())
                            .addToBackStack("")
                            .commit();
                }
            });
        }

    }
}
