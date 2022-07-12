package com.example.mytodo;

import java.time.LocalDateTime;

public class Notes implements iNotes {

    int id, count;
    String title, description;
    LocalDateTime date;

    @Override
    public Notes setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Notes setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Notes setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
