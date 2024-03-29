package com.example.mytodo.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytodo.R;

public class SettingsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.close) {
            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack();
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.findItem(R.id.add_note).setVisible(false);
        menu.findItem(R.id.settings).setVisible(false);
        menu.findItem(R.id.about).setVisible(false);
        menu.findItem(R.id.exit).setVisible(false);
        menu.findItem(R.id.clear_all_notes).setVisible(false);
        menu.findItem(R.id.close).setVisible(true);
    }
}