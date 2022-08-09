package com.example.mytodo.common;

import java.util.ArrayList;
import java.util.List;

public class NotesWork {
    public ArrayList<Notes> data = new ArrayList<>();


    public void initNotes(){
        data.add(new Notes("заметка 1 длиннный тайтл ывплаооапылвопаоыпваоыпва", "фывфыadsfdasfasdfвфыв"));
        data.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
        data.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
        data.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
        data.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
        data.add(new Notes("заметка 1", "фывфыadsfdasfasdfвфыв"));
        data.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
        data.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
        data.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
        data.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
        data.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
        data.add(new Notes("sdfsdfs", "asdasdassdafdsafsdafda"));
        data.add(new Notes("Приготовить еду", "рашгфрdsafasdfыагфыа"));
        data.add(new Notes("Приготовить еду", "рашгфрыаasdfsaгфыа"));
        data.add(new Notes("Приготовить еду", "рашгфрыаadsfasdfгфыа"));
        data.add(new Notes("Приготовить еду sfsdafadfaf", "рашгфрыагфыа"));
    }

    public void addNote(Notes note){
        data.add(note);

    }

    public void removeNote(Notes notes){
        data.remove(notes);

    }
}
