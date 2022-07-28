package com.example.mytodo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Notes> arrNotes;
    private final String[] colors = {"#AAAAAA", "#CCCCCC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            arrNotes = new ArrayList<>();
            arrNotes.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
            arrNotes.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
            arrNotes.add(new Notes("Приготовить еду", "рашгфрdsafasdfыагфыа"));
            arrNotes.add(new Notes("Приготовить еду", "рашгфрыаasdfsaгфыа"));
            arrNotes.add(new Notes("Приготовить еду", "рашгфрыаadsfasdfгфыа"));
            arrNotes.add(new Notes("Приготовить еду sfsdafadfaf", "рашгфрыагфыа"));
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
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showPopupMenu(v,index);
                    return true;
                }
            });
        }
    }
    private void showPopupMenu(View view, int index){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete_note:
                        arrNotes.remove(index);
                        initNotes();
                        return true;
                }
                return false;
            }
        });
        popupMenu.show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.add_note:
                Notes newNote = new Notes();
                arrNotes.add(newNote);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLL, NoteFragment.newInstance(newNote))
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.settings:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLL, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.about:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLL, new AboutFragment())
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.exit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
