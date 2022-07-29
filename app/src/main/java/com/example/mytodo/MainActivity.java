package com.example.mytodo;

import static com.example.mytodo.R.string.cancel;
import static com.example.mytodo.R.string.note_added;
import static com.example.mytodo.R.string.note_deleted;
import static com.example.mytodo.R.string.note_restored;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Notes> arrNotes;
    private final String KEY_NOTES = "notes";
    private final String[] colors = {"#AAAAAA", "#CCCCCC"};
    private FragmentManager fm = getSupportFragmentManager();


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
            arrNotes = savedInstanceState.getParcelableArrayList(KEY_NOTES);
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
            tv.setOnLongClickListener(v -> {
                showPopupMenu(v, index);
                return true;
            });

        }
    }

    private void showNote(Notes notes) {
        NoteFragment nf = NoteFragment.newInstance(notes);
        fm.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.frameLL, nf)
                .addToBackStack("")
                .commit();
    }

    private void showPopupMenu(View view, int index) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.delete_note:
                    deleteNote(index);
                    return true;
            }
            return false;
        });
        popupMenu.show();
    }


    public void deleteNote(int index) {
        Notes deletedNote = arrNotes.get(index);
        arrNotes.remove(index);
        initNotes();
        Snackbar.make(findViewById(R.id.frameLL), note_deleted, Snackbar.LENGTH_LONG)
                .setAction(cancel, v -> {
                    Notes backUpNote = deletedNote;
                    arrNotes.add(backUpNote);
                    initNotes();
                    Toast.makeText(this, note_restored, Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    public void deleteNote(Notes notes) {
        Notes deletedNote = notes;
        arrNotes.remove(notes);
        initNotes();
        Snackbar.make(findViewById(R.id.frameLL), note_deleted, Snackbar.LENGTH_LONG)
                .setAction(cancel, v -> {
                    Notes backUpNote = deletedNote;
                    arrNotes.add(backUpNote);
                    initNotes();
                    Toast.makeText(this, note_restored, Toast.LENGTH_SHORT).show();
                })
                .show();
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
                fm.beginTransaction()
                        .replace(R.id.frameLL, NoteFragment.newInstance(newNote))
                        .addToBackStack(null)
                        .commit();
                Toast.makeText(this, note_added, Toast.LENGTH_SHORT).show();
                return true;

            case R.id.settings:
                fm.beginTransaction()
                        .replace(R.id.frameLL, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.about:
                fm.beginTransaction()
                        .replace(R.id.frameLL, new AboutFragment())
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.exit:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.exit)
                        .setMessage(R.string.ask_exit)
                        .setPositiveButton(R.string.yes, (dialog, which) -> {
                            finish();
                            Toast.makeText(this, R.string.app_closed, Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_NOTES, arrNotes);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        arrNotes = savedInstanceState.getParcelableArrayList(KEY_NOTES);
    }
}
