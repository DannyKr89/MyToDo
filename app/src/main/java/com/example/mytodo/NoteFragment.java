package com.example.mytodo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;


public class NoteFragment extends Fragment {
    private Notes notes;
    private static final String ARG_NOTE = "note";


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

        if (getArguments() != null) {
            notes = getArguments().getParcelable(ARG_NOTE);
            String title = notes.getTitle();
            String description = notes.getDescription();

            etTitle.setText(title);
            etDescription.setText(description);
            tvDate.setText(String.valueOf(notes.getDate()));
        }


        requireActivity().findViewById(R.id.okBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.setTitle(etTitle.getText().toString());
                notes.setDescription(etDescription.getText().toString());
                updateNote();
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }


    private void updateNote() {
        ((MainActivity) requireActivity()).initNotes();
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
        MenuItem itemAdd = menu.findItem(R.id.add_note);
        if (itemAdd != null){
            itemAdd.setVisible(false);
        }
    }
}