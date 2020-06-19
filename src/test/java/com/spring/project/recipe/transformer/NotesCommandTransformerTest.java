package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Notes;
import com.spring.project.recipe.commands.NotesCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandTransformerTest {


    Long ID = 1L;
    public NotesCommandTransformer notesCommandTransformer;

    @BeforeEach
    void setUp(){
        notesCommandTransformer = new NotesCommandTransformer();
    }
    @Test
    void convert() {
        Notes notes = new Notes();
        notes.setId(ID);

        NotesCommand notesCommand = notesCommandTransformer.convert(notes);
        assertEquals(notes.getId(), notesCommand.getId());
    }

    @Test
    void testEmptyObject(){
        assertNotNull(notesCommandTransformer.convert(new Notes()));
    }

    @Test
    void testNullObject(){
        assertNull(notesCommandTransformer.convert(null));
    }
}