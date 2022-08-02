package com.example.mytodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private LayoutInflater inflater;
    ArrayList arrNotes;

    public NoteAdapter(Context context, ArrayList arrNotes) {
        this.inflater = LayoutInflater.from(context);
        this.arrNotes = arrNotes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        TextView
    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView textView;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.title_note);
        }
    }
}
