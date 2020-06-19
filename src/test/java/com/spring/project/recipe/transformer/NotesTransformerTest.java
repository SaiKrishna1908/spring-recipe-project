package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Notes;
import com.spring.project.recipe.commands.NotesCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class NotesTransformerTest {

    Long Id = 1L;

    NotesTransformer notesTransformer;
    @BeforeEach
    void setUp() {

        notesTransformer = new NotesTransformer();
    }

    @Test
    void convert() {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(Id);

        Notes notes = notesTransformer.convert(notesCommand);

        assertEquals(notesCommand.getId(), notes.getId());

    }

    @Test
    void testNullObject(){
        assertNull(notesTransformer.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(notesTransformer.convert(new NotesCommand()));
    }
}