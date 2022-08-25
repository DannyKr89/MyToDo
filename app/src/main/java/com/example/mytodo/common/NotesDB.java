package com.example.mytodo.common;

import java.util.ArrayList;

public class NotesDB {
    private ArrayList<Notes> Notes = new ArrayList<>();
    private static NotesDB instanceDB = null;

    public static NotesDB getInstanceDB(){
        if(instanceDB == null){
            instanceDB = new NotesDB();
        }
        return instanceDB;
    }

    public ArrayList<Notes> getNotes() {
        return Notes;
    }

    public void setNotes(ArrayList<com.example.mytodo.common.Notes> notes) {
        Notes = notes;
    }

    public void addNote(Notes note){
        Notes.add(note);

    }

    public void removeNote(Notes notes){
        this.Notes.remove(notes);

    }
}
