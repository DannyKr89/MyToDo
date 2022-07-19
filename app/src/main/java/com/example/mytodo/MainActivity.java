package com.example.mytodo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Notes> arrNotes;
    Date date = Calendar.getInstance().getTime();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrNotes = new ArrayList<>();

        arrNotes.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв", date));
        arrNotes.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрdsafasdfыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыаasdfsaгфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыаadsfasdfгфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготasdasdasdasfsadsggdgsdffggdfgfsdовить еду", "рашгфрыагфыа", date));
        arrNotes.add(new Notes("Приготовить еду", "рашгфрыагфыа", date));

        updateNotes();


    }

    public void updateNotes() {
        LinearLayout layout = findViewById(R.id.frameLL);
        String[] colors = {"#CCCCCC","#FFFFFF"};

        for (int i = 0; i < arrNotes.size(); i++) {
            AppCompatTextView tv = new AppCompatTextView(MainActivity.this);
            tv.setText(arrNotes.get(i).getTitle());
            tv.setTextSize(30);
            tv.setPadding(10,10,10,0);
            tv.setSingleLine(true);
            tv.setBackgroundColor(Color.parseColor(colors[i % 2]));

            layout.addView(tv);
            int index = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("note", arrNotes.get(index));
                    NotesFragment nf = new NotesFragment();
                    nf.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .add(R.id.frame, nf)
                            .addToBackStack("")
                            .commit();
                }
            });
        }
    }
}
