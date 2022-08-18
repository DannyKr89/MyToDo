package com.example.mytodo.common;

import java.util.ArrayList;

public interface INotesDB {
    public ArrayList<Notes> getNotes();
    public void setNotes(ArrayList<Notes> notes);
    public void addNote(Notes note);
    public void removeNote(Notes note);

}
