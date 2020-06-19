package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Notes;
import com.spring.project.recipe.commands.NotesCommand;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

//Takes a Command and return's Model
@RequiredArgsConstructor
@Component
public class NotesTransformer implements Converter<NotesCommand,Notes> {


    @Synchronized
    @Override
    @Nullable
    public Notes convert(NotesCommand notesCommand) {
        if(notesCommand == null)
            return null;

        final Notes notes = new Notes();
        notes.setNotes(notesCommand.getNotes());
        notes.setId(notesCommand.getId());


        return notes;
    }
}
