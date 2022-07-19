package com.example.mytodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;


public class NotesFragment extends Fragment {
    private Notes notes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatEditText etTitle = view.findViewById(R.id.etTitle);
        AppCompatEditText etDescription = view.findViewById(R.id.etDescription);
        AppCompatTextView tvDate = view.findViewById(R.id.tvDate);
        Bundle bundle = getArguments();
        notes = bundle.getParcelable("note");

        etTitle.setText(notes.getTitle());
        etDescription.setText(notes.getDescription());
        tvDate.setText(String.valueOf(notes.getDate()));



        requireActivity().findViewById(R.id.okBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    public static NotesFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NotesFragment fragment = new NotesFragment();
        fragment.setArguments(args);
        return fragment;
    }
}