package com.example.mytodo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Notes> arrNotes;
    private final String[] colors = {"#AAAAAA", "#CCCCCC"};
    Date date = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            arrNotes = new ArrayList<>();
            arrNotes.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв", date));
            arrNotes.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda", date));
            arrNotes.add(new Notes("Приготовить еду", "рашгфрdsafasdfыагфыа", date));
            arrNotes.add(new Notes("Приготовить еду", "рашгфрыаasdfsaгфыа", date));
            arrNotes.add(new Notes("Приготовить еду", "рашгфрыаadsfasdfгфыа", date));
            arrNotes.add(new Notes("Приготовить еду sfsdafadfaf", "рашгфрыагфыа", date));
        } else {
            arrNotes = savedInstanceState.getParcelableArrayList("notes");
        }
        initNotes();

    }

    public void initNotes() {
        LinearLayout layout = findViewById(R.id.frameNotes);
        layout.removeAllViews();
        for (int i = 0; i < arrNotes.size(); i++) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(arrNotes.get(i).getTitle());
            tv.setTextSize(30);
            tv.setPadding(10, 10, 10, 10);
            tv.setSingleLine(true);
            tv.setBackgroundColor(Color.parseColor(colors[i % 2]));
            layout.addView(tv);
            final int index = i;
            tv.setOnClickListener(v -> {
                showNote(arrNotes.get(index));
            });
        }
    }

    private void showNote(Notes notes) {
        NoteFragment nf = NoteFragment.newInstance(notes);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.frameLL, nf)
                .addToBackStack("")
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("notes", arrNotes);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        arrNotes = savedInstanceState.getParcelableArrayList("notes");
    }
}
