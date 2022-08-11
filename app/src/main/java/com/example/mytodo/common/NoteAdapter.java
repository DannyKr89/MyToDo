package com.example.mytodo.common;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodo.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private final int[] cardColors = {0xFFAAAAAA, 0xFFCCCCCC};
    ArrayList<Notes> data;

    OnItemClickListener itemClickListener;
    OnItemLongClickListener itemLongClickListener;


    @SuppressLint("NotifyDataSetChanged")
    public void initNotes(ArrayList<Notes> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Notes note = data.get(position);
        holder.bindContent(note);
    }

    @Override
    public int getItemCount() {
        return data.size();
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

        private void bindContent(Notes note){
            textViewTitle.setText(note.getTitle());
            textViewTitle.setSelected(true);
            textViewDescription.setText(note.getDescription());
            textViewDate.setText(note.getDate());
            textViewId.setText(String.valueOf(note.getId() + 1));
            textViewId.setBackgroundColor(note.getColor());
            cardView.setCardBackgroundColor(cardColors[note.getId() % 2]);
        }

    }







}
