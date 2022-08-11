package com.example.mytodo.ui;

import static com.example.mytodo.R.string.cancel;
import static com.example.mytodo.R.string.note_deleted;
import static com.example.mytodo.R.string.note_restored;
import static com.example.mytodo.R.string.notes_restored;

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
import com.example.mytodo.common.NotesDB;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    NotesDB notesDB = NotesDB.getInstanceDB();
    private final String KEY_NOTES = "notes";
    private final FragmentManager fm = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);
        noteAdapter.SetOnItemClickListener((view, position) -> showNote(notesDB.getNotes().get(position)));
        noteAdapter.SetOnItemLongClickListener(this::showPopupMenu);


        if (savedInstanceState != null) {
            notesDB.setNotes(savedInstanceState.getParcelableArrayList(KEY_NOTES));
        }

        initNotes();

    }

    public void initNotes() {
        noteAdapter.initNotes(notesDB.getNotes());
    }

    public void deleteNotes(Notes note) {
        noteAdapter.notifyItemRemoved(note.getId());
    }

    public void changeNotes(Notes note) {
        noteAdapter.notifyItemChanged(note.getId());
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
            switch (item.getItemId()) {
                case R.id.delete_note:
                    deleteNote(index);
                    return true;
                case R.id.change_note:
                    showNote(notesDB.getNotes().get(index));
                    return true;
            }
            return false;
        });
        popupMenu.show();
    }

    public void newNote() {
        Notes newNote = new Notes();
        notesDB.addNote(newNote);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLL, NoteFragment.newInstance(newNote))
                .addToBackStack(null)
                .commit();
    }

    public void deleteNote(int index) {
        Notes deletedNote = notesDB.getNotes().get(index);
        deleteNote(deletedNote);
    }

    public void deleteNote(Notes note) {
        notesDB.removeNote(note);
        deleteNotes(note);
        makeSnackbarDeleteNotes(note);
    }

    public void makeSnackbarDeleteNotes(Notes note) {
        Snackbar.make(findViewById(R.id.frameLL), note_deleted, Snackbar.LENGTH_LONG)
                .setAction(cancel, v -> {
                    notesDB.addNote(note);
                    changeNotes(note);
                    Toast.makeText(this, note_restored, Toast.LENGTH_SHORT).show();

                })
                .show();
    }

    public void makeSnackbarClearAllNotes(ArrayList<Notes> notes) {
        Snackbar.make(findViewById(R.id.frameLL), R.string.all_notes_clear, Snackbar.LENGTH_LONG)
                .setAction(cancel, v -> {
                    notesDB.setNotes(notes);
                    initNotes();
                    Toast.makeText(this, notes_restored, Toast.LENGTH_SHORT).show();

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

            case R.id.clear_all_notes:
                ArrayList<Notes> notesBackup = new ArrayList<>(notesDB.getNotes());
                notesDB.getNotes().clear();
                initNotes();
                makeSnackbarClearAllNotes(notesBackup);
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
        outState.putParcelableArrayList(KEY_NOTES, notesDB.getNotes());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        notesDB.setNotes(savedInstanceState.getParcelableArrayList(KEY_NOTES));
    }
}
