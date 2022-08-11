package com.example.mytodo.common;

import java.util.ArrayList;
import java.util.List;

public class NotesWork {
    public ArrayList<Notes> data = new ArrayList<>();

    public void addNote(Notes note){
        data.add(note);

    }

    public void removeNote(Notes notes){
        data.remove(notes);

    }
}
