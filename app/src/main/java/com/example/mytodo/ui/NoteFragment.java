package com.example.mytodo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytodo.R;
import com.example.mytodo.common.Notes;
import com.example.mytodo.common.NotesDB;


public class NoteFragment extends Fragment {

    private Notes notes;
    private static final String ARG_NOTE = "note";
    NotesDB notesDB = NotesDB.getInstanceDB();

    private void hideKeyboard(){
        View view = this.getView();
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_note, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText etTitle = view.findViewById(R.id.etTitle);
        EditText etDescription = view.findViewById(R.id.etDescription);
        TextView tvDate = view.findViewById(R.id.tvDate);
        ImageView imgBttn = view.findViewById(R.id.okBtn);

        if (getArguments() != null) {
            notes = getArguments().getParcelable(ARG_NOTE);
            String title = notes.getTitle();
            String description = notes.getDescription();

            etTitle.setText(title);
            etDescription.setText(description);
            tvDate.setText(notes.getDate());
            imgBttn.setBackgroundColor(notes.getColor());
        }


        imgBttn.setOnClickListener(v -> {
            if (etTitle.getText().toString().equals("")) {
                Toast.makeText(requireActivity(), R.string.title_is_empty, Toast.LENGTH_SHORT).show();
            } else {
                notes.setTitle(etTitle.getText().toString());
                notes.setDescription(etDescription.getText().toString());
                Toast.makeText(requireActivity(), R.string.note_saved, Toast.LENGTH_SHORT).show();
                requireActivity()
                        .getSupportFragmentManager()
                        .popBackStack();
                hideKeyboard();
                updateNote();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            ((MainActivity) requireActivity()).deleteNote(notes);
            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack();
            hideKeyboard();
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateNote() {
        ((MainActivity) requireActivity()).noteAdapter.notifyItemChanged(notes.getId());
        ((MainActivity) requireActivity()).saveToJson(notesDB.getNotes());
    }

    public static NoteFragment newInstance(Notes notes) {
        NoteFragment noteFragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, notes);
        noteFragment.setArguments(args);
        return noteFragment;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.findItem(R.id.delete).setVisible(true);
        menu.findItem(R.id.add_note).setVisible(false);
        menu.findItem(R.id.clear_all_notes).setVisible(false);
        }

}