package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Notes;
import com.spring.project.recipe.commands.NotesCommand;
import org.springframework.lang.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// Take'a model and return'a Command
@Component
public class NotesCommandTransformer implements Converter<Notes, NotesCommand> {


    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes == null)
            return null;

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setNotes(notes.getNotes());


        return notesCommand;
    }
}
