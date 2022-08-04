package com.example.mytodo.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodo.R;

import java.util.ArrayList;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private LayoutInflater inflater;
    private final int[] cardColors = {0xFFAAAAAA, 0xFFCCCCCC};
    ArrayList<Notes> arrNotes;

    OnItemClickListener itemClickListener;
    OnItemLongClickListener itemLongClickListener;



    public NoteAdapter(Context context, ArrayList<Notes> arrNotes) {
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
        Notes note = arrNotes.get(position);
        CardView cardView = holder.cardView;
        TextView textViewTitle = holder.textViewTitle;
        TextView textViewDescription = holder.textViewDescription;
        TextView textViewDate = holder.textViewDate;
        TextView textViewId = holder.textViewId;

        textViewTitle.setText(note.getTitle());
        textViewTitle.setSelected(true);
        textViewDescription.setText(note.getDescription());
        textViewDate.setText(note.getDate());
        textViewId.setText(String.valueOf(note.getId() + 1));
        textViewId.setBackgroundColor(note.getColor());
        cardView.setCardBackgroundColor(cardColors[position % 2]);
    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }


    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void SetOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewDate;
        TextView textViewId;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_note);
            cardView.setOnClickListener(v -> itemClickListener.onItemClick(v, getAdapterPosition()));
            cardView.setOnLongClickListener(v -> {
                itemLongClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            });

            textViewTitle = itemView.findViewById(R.id.title_note);
            textViewDescription = itemView.findViewById(R.id.description_note);
            textViewDate = itemView.findViewById(R.id.date_note);
            textViewId = itemView.findViewById(R.id.id_note);
        }

    }







}
