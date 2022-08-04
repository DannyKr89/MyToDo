package com.example.mytodo.ui;

import static com.example.mytodo.R.string.cancel;
import static com.example.mytodo.R.string.note_deleted;
import static com.example.mytodo.R.string.note_restored;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodo.R;
import com.example.mytodo.common.NoteAdapter;
import com.example.mytodo.common.Notes;
import com.example.mytodo.presenter.NotePresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Notes> arrNotes = new ArrayList<>();
    RecyclerView recyclerView;
    NotePresenter presenter;
    NoteAdapter noteAdapter;
    private final String KEY_NOTES = "notes";
    private final FragmentManager fm = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        noteAdapter = new NoteAdapter(this, arrNotes);


        if (savedInstanceState == null) {
            arrNotes = new ArrayList<>();
            arrNotes.add(new Notes("заметка 1 длиннный тайтл ывплаооапылвопаоыпваоыпва", "фывфыadsfdasfasdfвфыв"));
            arrNotes.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
            arrNotes.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
            arrNotes.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
            arrNotes.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
            arrNotes.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
            arrNotes.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
            arrNotes.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
            arrNotes.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
            arrNotes.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
            arrNotes.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
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
        noteAdapter = new NoteAdapter(this, arrNotes);
        recyclerView.setAdapter(noteAdapter);
        noteAdapter.SetOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showNote(arrNotes.get(position));
            }
        });
        noteAdapter.SetOnItemLongClickListener(new NoteAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                showPopupMenu(view, position);
            }
        });

    }

    private void showNote(Notes notes) {
        NoteFragment nf = NoteFragment.newInstance(notes);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.frameLL, nf)
                .addToBackStack(null)
                .commit();
    }

    private void showPopupMenu(View v, int index) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popup);
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.delete_note) {
                deleteNote(index);
                return true;
            }
            return false;
        });
        popupMenu.show();
    }

    public void newNote() {
        Notes newNote = new Notes();
        arrNotes.add(newNote);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLL, NoteFragment.newInstance(newNote))
                .addToBackStack(null)
                .commit();
    }

    public void deleteNote(int index) {
        Notes deletedNote = arrNotes.get(index);
        arrNotes.remove(index);
        initNotes();
        makeSnackbar(deletedNote);
    }

    public void deleteNote(Notes notes) {
        arrNotes.remove(notes);
        initNotes();
        makeSnackbar(notes);
    }

    public void makeSnackbar(Notes note) {
        Snackbar.make(findViewById(R.id.frameLL), note_deleted, Snackbar.LENGTH_LONG)
                .setAction(cancel, v -> {
                    arrNotes.add(note);
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_note:
                newNote();
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
                showAlertDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.exit)
                .setMessage(R.string.ask_exit)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    finish();
                    Toast.makeText(this, R.string.app_closed, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.no, null)
                .show();
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
