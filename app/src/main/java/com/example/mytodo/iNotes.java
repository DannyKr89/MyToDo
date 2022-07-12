package com.example.mytodo;

import java.time.LocalDateTime;

public interface iNotes {
    Notes setTitle(String title);

    Notes setDescription(String description);

    Notes setDate(LocalDateTime date);
}
