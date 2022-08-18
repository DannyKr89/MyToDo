package com.example.mytodo.common;

import java.util.ArrayList;

public class NotesDB implements INotesDB {
    private ArrayList<Notes> notes = new ArrayList<>();
    private static NotesDB instanceDB = null;

    public static NotesDB getInstanceDB() {
        if (instanceDB == null) {
            instanceDB = new NotesDB();
        }
        return instanceDB;
    }

    public ArrayList<Notes> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<com.example.mytodo.common.Notes> notes) {
        this.notes = notes;
    }


    public void addNote(Notes note) {
        notes.add(note);

    }

    public void removeNote(Notes notes) {
        this.notes.remove(notes);

    }
}
